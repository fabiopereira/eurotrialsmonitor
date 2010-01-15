package me.fabiopereira.eurotrialsmonitor.model;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class FormularioRespondido extends PersistedModel {

	private static final long serialVersionUID = 20100103L;

	@Persistent
	private String estudo;
	@Persistent
	private String centro;
	@Persistent
	private Integer numeroVisita;
	@Persistent
	private Date dataVisita;
	@Persistent(mappedBy = "formularioRespondido")
	private List<EtapaRespondida> etapaRespondidas;
	@Persistent
	private Monitor monitor;

	public FormularioRespondido() {
	}

	public FormularioRespondido(Monitor monitor) {
		this.setMonitor(monitor);
	}

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

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	public Monitor getMonitor() {
		return monitor;
	}
}
