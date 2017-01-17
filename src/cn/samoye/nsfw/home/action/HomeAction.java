package cn.samoye.nsfw.home.action;

import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport{
	//nsfw首页
	public String frame(){
		return "frame";
	}
	//nsfw顶部
	public String top(){
		return "top";
	}
	//nsfw左侧
	public String left(){
		return "left";
	}
}
