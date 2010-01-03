package me.fabiopereira.eurotrialsmonitor.model;

import java.util.Date;
import java.util.List;

public class FormularioRespondido extends PersistedModel {

	private static final long serialVersionUID = 20100103L;

	private String estudo;
	private String centro;
	private Integer numeroVisita;
	private Date dataVisita;
	private List<EtapaRespondida> etapaRespondidas;

	public String getEstudo() {
		return estudo;
	}

	public void setEstudo(String estudo) {
		this.estudo = estudo;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public Integer getNumeroVisita() {
		return numeroVisita;
	}

	public void setNumeroVisita(Integer numeroVisita) {
		this.numeroVisita = numeroVisita;
	}

	public Date getDataVisita() {
		return dataVisita;
	}

	public void setDataVisita(Date dataVisita) {
		this.dataVisita = dataVisita;
	}

	public List<EtapaRespondida> getEtapaRespondidas() {
		return etapaRespondidas;
	}

	public void setEtapaRespondidas(List<EtapaRespondida> etapaRespondidas) {
		this.etapaRespondidas = etapaRespondidas;
	}

}
