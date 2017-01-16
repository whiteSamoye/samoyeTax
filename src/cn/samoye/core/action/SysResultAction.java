package cn.samoye.core.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;
/**
 * 对Struts返回结果进行处理
 * 	比如:当登录也出现异常后,返回到登录页,此时全局异常映射不再适用
 * @author samoye
 *
 */
public class SysResultAction extends StrutsResultSupport {

	@Override
	protected void doExecute(String arg0, ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		BaseAction action = (BaseAction) invocation.getAction();
		
		System.out.println("SysResultAction.doExecute()");
	}

}
