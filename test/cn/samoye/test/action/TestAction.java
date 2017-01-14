package cn.samoye.test.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.samoye.test.service.TestService;
@Controller(value="testAction")
@Scope(value="prototype")
public class TestAction extends ActionSupport{
	public TestAction() {
		System.out.println("action实例创建...");
	}
	
	@Resource
	private TestService testService;
	
	@Override
	public String execute() throws Exception {
		testService.say();
		return SUCCESS;
	}
}
