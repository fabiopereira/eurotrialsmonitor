package me.fabiopereira.eurotrialsmonitor.model;

import java.util.List;


public class FormulariosRespondidos {
	private final List<FormularioRespondido> list;

	public FormulariosRespondidos(List<FormularioRespondido> list) {
		this.list = list;
	}

	public List<FormularioRespondido> getList() {
		return list;
	}
	 
}
