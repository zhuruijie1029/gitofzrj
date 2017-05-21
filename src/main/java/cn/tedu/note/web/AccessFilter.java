package cn.tedu.note.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.note.entity.User;

/**
 * Servlet Filter implementation class AccessFilter
 */
public class AccessFilter implements Filter {
  
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(
		ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//类型转换
		HttpServletRequest req = 
			(HttpServletRequest) request;
		HttpServletResponse res = 
			(HttpServletResponse) response;
		//1. 获取登录用户信息
		//2. 如果没有登录用户信息，重定向到 log_in.html
		String path = req.getRequestURI();
		System.out.println(path); 
		
		
		if(path.endsWith("log_in.html") ||
			path.contains("/alert/")){ 
			//设置HTTP协议头，避免浏览器缓存html页面
			res.addHeader("Cache-Control", "no-cache");
			chain.doFilter(request, response);
			return;
		}
		
		User user = (User)req.getSession()
			.getAttribute("loginUser");
		if(user==null){
			//如果user为null则表示没有登录
			//重定向到 log_in.html
			//采用绝对路径重定向！可以避免错误
			String login = 
				req.getContextPath()+ 
				"/log_in.html";
			// /note/log_in.html
			res.sendRedirect(login);
			return;
		}
		//设置HTTP协议头，避免浏览器缓存html页面
		res.addHeader("Cache-Control", "no-cache");
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
