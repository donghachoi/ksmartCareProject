package com.cafe24.choiyooq1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	//request에서 세션값을 받아 HttpSession 클래스를 참조한 session 참조변수에 담습니다.
	//그리고 session을 통해 SID값을 받아서 sId에 담고 null일때 /login 들어가게 됩니다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String sId = (String) session.getAttribute("SID");
		if(sId == null) {
			response.sendRedirect("/login");
		}
		return super.preHandle(request, response, handler);
	}
}