package me.fabiopereira.eurotrialsmonitor.controller;

import javax.jdo.PersistenceManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.fabiopereira.eurotrialsmonitor.repository.CurrentPersistenceManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Service
public class OpenPersistenceManagerInViewInterceptor extends HandlerInterceptorAdapter {

	private PersistenceManagerFactory pmf;

	@Autowired
	public OpenPersistenceManagerInViewInterceptor(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		CurrentPersistenceManager.set(pmf.getPersistenceManager());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		CurrentPersistenceManager.close();
	}

}
