package me.fabiopereira.eurotrialsmonitor.model;

import java.io.Serializable;

public class MonitorLogin implements Serializable {

	private static final long serialVersionUID = 20100103L;
	
	String id;

	public MonitorLogin() {
	}    
	
	public MonitorLogin(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
