package me.fabiopereira.eurotrialsmonitor.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;

@Service
public class MonitorLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String pathInfo = request.getPathInfo();
		if (pathInfo.contains("monitorLogin")) {
			return true;
		}
		Object monitorObj = request.getSession().getAttribute("monitor");
		if (monitorObj == null) {
			response.sendRedirect("monitorLogin");
			return false;
		}	
		return true;
	}	
}
