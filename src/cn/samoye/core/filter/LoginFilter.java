package cn.samoye.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.samoye.core.constant.Constant;
import cn.samoye.core.permission.PermissionCheck;
import cn.samoye.nsfw.user.bean.User;

public class LoginFilter implements Filter{


	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		HttpServletResponse res = (HttpServletResponse) servletResponse;
		String uri = req.getRequestURI();
		if(uri.contains("sys/login_")){
			//登录请求直接放心
			chain.doFilter(req, res);
		}else{
			//非登录请求
			if(req.getSession().getAttribute(Constant.USER) != null){
				//已经登录
				//权限鉴定
				if(uri.contains("/nsfw/")){//对纳税服务子系统进行权限鉴定
					User user = (User) req.getSession().getAttribute(Constant.USER);
					//服务器启动时,实例化的那个ioc容器
//					WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
					WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(req.getSession().getServletContext());
					//permissionCheck,对象这里不使用new的方式创建,不然,对性能有所影响
					PermissionCheck pc = (PermissionCheck) applicationContext.getBean("permissionCheck");
					if(pc.isAccessible(user,"nsfw")){
						chain.doFilter(req, res);
					}else{
						//跳转到没有权限提示界面
						res.sendRedirect(req.getContextPath()+"/sys/login_noPermissionUI.action");
					}
				}else if(uri.contains("wdkj")){
					// do samething
					chain.doFilter(req, res);
				}else{
					chain.doFilter(req, res);
				}
				
				
			}else{
				//未登录
				res.sendRedirect(req.getContextPath()+"/sys/login_toLoginUI.action");
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
