package cn.tedu.note.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.tedu.note.entity.User;

@Component("accessInterceptor")
public class AccessInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	public boolean preHandle(
			HttpServletRequest req, 
			HttpServletResponse res, 
			Object handler) throws Exception {
		//查询session中是否保存了登录用户信息
		//如果没有登录 返回一个JSON，包含“需要登录”消息
		//如果已经登录，则通过 返回 true
		User user=(User)req.getSession()
				.getAttribute("loginUser");
		System.out.println("user:"+user);
		if(user==null){
			String json=
				"{\"state\":1,\"message\":\"需要登录\"}";
			res.setContentType("text/html; charset=utf-8");
			res.getWriter().print(json);
			return false;
		}
		return true;
	}

}
