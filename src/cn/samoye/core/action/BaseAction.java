package cn.samoye.core.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 对action公有的方法进行抽取
 * 	比如:分页.批量删除
 * @author samoye
 *
 */
public class BaseAction extends ActionSupport{
	//批量删除
	protected String[] selectedRow;
	
	
	
	//---------数据封装
	public String[] getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
}
