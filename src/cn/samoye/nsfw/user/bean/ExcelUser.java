package cn.samoye.nsfw.user.bean;

import cn.samoye.core.annotation.ExcelClassAnnotation;
import cn.samoye.core.annotation.ExcelFieldAnnotation;
import cn.samoye.core.annotation.ExcelFiledBoolean;

@ExcelClassAnnotation(
		columnNames={"用户名","账号","所属部门","性别","电子邮箱"},
		beanPropertyNames={"name","account","dept","gender","email"},
		titleName="用户列表",
		titleFontSize=16,
		columnNameSize=14
		)
public class ExcelUser {
	@ExcelFieldAnnotation(beanPropertyName="name")
	private String name;
	@ExcelFieldAnnotation(beanPropertyName="account")
	private String account;
	@ExcelFieldAnnotation(beanPropertyName="dept")
	private String dept;
	@ExcelFieldAnnotation(beanPropertyName="email")
	private String email;
	@ExcelFieldAnnotation(beanPropertyName="gender")
	@ExcelFiledBoolean(isTure="男",isFalse="女")
	private String gender;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
