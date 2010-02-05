package me.fabiopereira.eurotrialsmonitor.controller;

import javax.servlet.http.HttpServletRequest;

public class EurotrialsRequest {

	private final HttpServletRequest request;

	public EurotrialsRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void echoAllParams() {
		for (Object paramKey : request.getParameterMap().keySet()) {
			String key = paramKey + "";
			request.setAttribute(key, request.getParameter(key));
		}
	}

}
