package me.fabiopereira.eurotrialsmonitor.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;


public class FormulariosRespondidos {
	private final List<FormularioRespondido> list;

	public FormulariosRespondidos(List<FormularioRespondido> list) {
		this.list = list;
	}

	public List<FormularioRespondido> getList() {
		return list;
	}

	public FormulariosRespondidos filterByUsuario(String monitorUsuarioParam) {
		if (StringUtils.isBlank(monitorUsuarioParam)) {
			return this;
		}
		
		List<FormularioRespondido> formulariosRespondidosFiltered = new ArrayList<FormularioRespondido>();
		for (FormularioRespondido formularioRespondido : list) {
			if (formularioRespondido.getMonitor().getUsuario().equals(monitorUsuarioParam)) {
				formulariosRespondidosFiltered.add(formularioRespondido);
			}
		}
		return new FormulariosRespondidos(formulariosRespondidosFiltered);
	}
	
	public Double getKpi() {
		return KPICalculator.getKpi(getPerguntasRespondidas());
	}

	public String getKpiAsString() {
		return KPICalculator.getKpiAsString(getPerguntasRespondidas());
	}

	private PerguntasRespondidas getPerguntasRespondidas() {
		List<PerguntaRespondida> perguntasRespondidas = new ArrayList<PerguntaRespondida>();
		for (FormularioRespondido formularioRespondido : list) {
			if (formularioRespondido.getKpi() != null) {
				perguntasRespondidas.addAll(formularioRespondido.getPerguntasRespondidas().getList());
			}
		}
		return new PerguntasRespondidas(perguntasRespondidas);
	}	
	 
}
