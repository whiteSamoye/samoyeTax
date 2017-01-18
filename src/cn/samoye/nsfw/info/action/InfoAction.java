package cn.samoye.nsfw.info.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.samoye.core.action.BaseAction;
import cn.samoye.core.exception.ActionException;
import cn.samoye.core.exception.SysException;
import cn.samoye.nsfw.info.bean.Info;
import cn.samoye.nsfw.info.service.InfoService;

public class InfoAction extends BaseAction {
	private final static Log log = LogFactory.getLog(InfoAction.class);
	@Resource
	private InfoService infoService;
	private List<Info> infoList;
	private Info info;
	
	//1.列表页面
	public String listUI()throws SysException{
		//加载分类集合
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
		try {
			infoList = infoService.queryInfoList();
		} catch (Exception e) {
			throw new ActionException("action处理: "+e.getMessage());
		}
		return "listUI";
	}
	//2.新增页面
	public String addUI(){
		//加载分类集合
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
		info = new Info();
		info.setCreateTime(new Timestamp(new Date().getTime()));
		return "addUI";
	}
	
	//3.保存新增
	public String add(){
		if(info != null){
			infoService.save(info);
		}
		return "list";
	}
	//4.更新页面
	public String updateUI(){
		//加载分类集合
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
		if (info != null && info.getInfoId() != null) {
			info = infoService.queryInfoById(info.getInfoId());
		}
		return "updateUI";
	}
	/**
	 * 5.保存更新
	 * @return
	 */
	public String update(){
		if(info != null){
			infoService.update(info);
		}
		return "list";
	}
	/**
	 * 6.删除
	 * 删除的同时,删除该用户对应的头像信息
	 * @return
	 */
	public String delete(){
		if(info != null && StringUtils.isNotBlank(info.getInfoId())){
			info = infoService.queryInfoById(info.getInfoId());
			infoService.delete(info.getInfoId());
		}
		return "list";
	}
	//7.批量删除
	public String deletes(){
		if(selectedRow != null && selectedRow.length > 0){
			for (String id : selectedRow) {
				infoService.delete(id);
			}
		}
		return "list";
	}
	
	//异步发布信息
	public void publicInfo(){
		try {
			if(info != null){
				//1、更新信息状态
				Info tem = infoService.queryInfoById(info.getInfoId());
				tem.setState(info.getState());
				infoService.update(tem);
				
				//2、输出更新结果
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write("更新状态成功".getBytes("utf-8"));
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ---------------------------------数据的封装
	 */
	
	public List<Info> getInfoList() {
		return infoList;
	}
	public void setInfoList(List<Info> infoList) {
		this.infoList = infoList;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	
	
	
}
