package cn.samoye.login.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.samoye.core.constant.Constant;
import cn.samoye.nsfw.user.bean.User;
import cn.samoye.nsfw.user.service.UserService;
/**
 * 登录：
1、获取帐号和密码
2、通过帐号和密码查询用户表；如果查询有记录说登录成功；否则登录失败跳转到登录页面并且提示用户登录失败信息
2.1、登录成功
2.1.1、将用户信息保存到session中
2.1.2、把用户的角色设置到用户中
2.1.3、将用户登录记录到日志文件
2.1.4、重定向跳转到首页


2.2、登录失败
     跳转到登录页面并且提示用户登录失败信息



退出（注销）：清除session中保存的用户信息
 */
public class LoginAction extends ActionSupport{
	private static Log log = LogFactory.getLog(LoginAction.class);
	@Resource
	private UserService userService;
	private User user;
	private String loginResult;
	
	public String toLoginUI(){
		return "toLoginUI";
	}
	
	public String login(){
		if(user != null){
			if(StringUtils.isNotBlank(user.getAccount()) && StringUtils.isNotBlank(user.getPassword())){
				List<User> userList = userService.loginCheck(user.getAccount(),user.getPassword());
				if(userList != null && userList.size()>0){
					user.setUserRole(userService.queryUserRoleByUserId(user.getId()));
					ServletActionContext.getRequest().getSession().setAttribute(Constant.USER, userList.get(0));
					log.info("用户: "+ userList.get(0).getName()+"登录系统!");
					return "home";
				}
			}else{
				loginResult = "账号或密码不能为空!";
			}
		}else{
			loginResult = "请输入账号和密码";
		}
		return "toLoginUI";
	}
	//退出
	public String logout(){
		ServletActionContext.getRequest().getSession().removeAttribute(Constant.USER);
		return "toLoginUI";
	}
	//跳转到没有权限提示页面
	public String noPermissionUI(){
		return "noPermissionUI";
	}
///----------------------------
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLoginResult() {
		return loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
	
	
}
