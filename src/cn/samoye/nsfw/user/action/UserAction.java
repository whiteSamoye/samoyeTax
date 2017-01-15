package cn.samoye.nsfw.user.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionSupport;

import cn.samoye.nsfw.user.bean.User;
import cn.samoye.nsfw.user.service.UserService;

public class UserAction extends ActionSupport {
	private final static Log log = LogFactory.getLog(UserAction.class);
	@Resource
	private UserService userService;
	private List<User> userList;
	private User user;
	//批量删除
	private String[] selectedRow;
	//头像上传
	private File headImg;
	private String headImgFileName;
	private String headImgContentType;
	//1.列表页面
	public String listUI(){
		userList = userService.queryUserList();
		return "listUI";
	}
	//2.新增页面
	public String addUI(){
		return "addUI";
	}
	
	//3.保存新增
	public String add(){
		String path = this.uploadPic();
		user.setHeadImg(path);
		userService.save(user);
		return "list";
	}
	//4.更新页面
	public String updateUI(){
		if(user != null && StringUtils.isNotBlank(user.getId())){
			user = userService.queryUserById(user.getId());
		}
		return "updateUI";
	}
	/**
	 * 5.保存更新
	 * @return
	 */
	public String update(){
		if(headImg != null && StringUtils.isNotBlank(user.getHeadImg())){
			this.deleteImg(user.getHeadImg());
		}
		if(headImg != null){
			String path = this.uploadPic();
			user.setHeadImg(path);
		}
		userService.update(user);
		return "list";
	}
	/**
	 * 6.删除
	 * 删除的同时,删除该用户对应的头像信息
	 * @return
	 */
	public String delete(){
		if(user != null && StringUtils.isNotBlank(user.getId())){
			user = userService.queryUserById(user.getId());
			userService.delete(user.getId());
			if(StringUtils.isNotBlank(user.getHeadImg())){
				this.deleteImg(user.getHeadImg());
			}
		}
		return "list";
	}
	//7.批量删除
	public String deletes(){
		if(selectedRow != null && selectedRow.length > 0){
			for (String id : selectedRow) {
				userService.delete(id);
			}
		}
		return "list";
	}
	
	private boolean deleteImg(String path){
		try {
			String imgPath = ServletActionContext.getServletContext().getRealPath("/upload");
			String str = imgPath+"/"+path;
			return new File(str).delete();
		} catch (Exception e) {
			log.error("用户id为: "+user.getId()+"文件删除失败", e);
		}

		return false;
	}
	
	public String plopload(){
		String pic = this.uploadPic();
		System.out.println(pic);
		return "list";
	}
	//8.头像上传
	private String uploadPic(){
		String path = "";
		if(headImg != null){
			try {
				String imgPath = ServletActionContext.getServletContext().getRealPath("/upload/user");
				String newFileName = UUID.randomUUID().toString().replace("-", "")+headImgFileName.substring(headImgFileName.lastIndexOf("."));
				//org.springframework.util.FileCopyUtils;
//				FileCopyUtils.copy(headImg, new File(imgPath,newFileName));
				//org.apache.commons.io.FileUtils;
				FileUtils.copyFile(headImg, new File(imgPath,newFileName));
				path = "user/"+newFileName;
				System.out.println(path);
			} catch (IOException e) {
				log.error("用户id为: "+user.getId()+"上传图片出错", e);
			}
		}
		System.out.println(path);
		return path;
	}
	
	// 9.excel导出
	public void exportExcel(){
		try {
			userList = userService.queryUserList();
			HttpServletResponse response = ServletActionContext.getResponse();
			//设置浏览器的内容类型问文件格式
			response.setContentType("application/x-excel");
			//设置浏览器以附件下载的形式加载文件
			response.setHeader("Content-disposition", "attachment;filename="+new String("用户列表.xsl".getBytes(),"iso-8859-1"));
			ServletOutputStream ops = response.getOutputStream();
			userService.exportExcelService(userList,ops);
			if(ops != null){
				ops.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String importExcel(){
		//判断文件对象是否为null
		if(headImg != null){
			//判断导入的是否是excel文件
			if(headImgFileName.matches("^.+\\.(?i)((xsl)|(xlsx))$")){
				userService.importExcel(headImg,headImgFileName);
			}
		}
		return "list";
	}
	
	//账户校验
	public void verifyAccount(){
		try {
			//1.获取账号
			if(user != null && StringUtils.isNotBlank(user.getAccount())){
				//2.账号校验
				List<User> userList = userService.queryUserListByAccountAndId(user.getAccount(),user.getId());
				String result = "true";
				if(userList != null && userList.size() > 0){
					result = "false";
				}
				
				//3.返回结果
				HttpServletResponse response = ServletActionContext.getResponse();
				//设置浏览器的内容类型问文件格式
				response.setContentType("text/html");
				//设置浏览器以附件下载的形式加载文件
				ServletOutputStream ops = response.getOutputStream();
				ops.write(result.getBytes());
				ops.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * ---------------------------------数据的封装
	 */
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String[] getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
	public File getHeadImg() {
		return headImg;
	}
	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}
	public String getHeadImgFileName() {
		return headImgFileName;
	}
	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}
	public String getHeadImgContentType() {
		return headImgContentType;
	}
	public void setHeadImgContentType(String headImgContentType) {
		this.headImgContentType = headImgContentType;
	}
	
	
}
