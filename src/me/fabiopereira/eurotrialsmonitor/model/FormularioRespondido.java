package me.fabiopereira.eurotrialsmonitor.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import me.fabiopereira.eurotrialsmonitor.exception.CampoInvalidoException;

import org.apache.commons.lang.StringUtils;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class FormularioRespondido extends PersistedModel {

	private static final long serialVersionUID = 20100103L;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Persistent
	private String estudo;
	@Persistent
	private String centro;
	@Persistent
	private Integer numeroVisita;
	@Persistent
	private Date dataVisita;
	@Persistent()
	private List<EtapaRespondida> etapaRespondidas;
	@Persistent
	private Monitor monitor;
	@Persistent
	private FormularioRespondidoStatus status;

	public FormularioRespondido() {
	}

	public FormularioRespondido(Monitor monitor) {
		if (monitor == null) {
			throw new IllegalArgumentException("Monitor nao pode ser nulo");
		}
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

	public String getDataVisitaAsString() {
		return dataVisita != null ? sdf.format(dataVisita) : null;

	}

	public void setDataVisita(Date dataVisita) {
		this.dataVisita = dataVisita;
	}

	public void setDataVisitaAsString(String dataVisita) {
		if (StringUtils.isNotBlank(dataVisita))
			try {
				this.dataVisita = sdf.parse(dataVisita);
			} catch (ParseException e) {
				throw new CampoInvalidoException("Data da Visita", dataVisita);
			}
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

	public FormularioRespondidoStatus getStatus() {
		return status;
	}

	public void setStatus(FormularioRespondidoStatus status) {
		this.status = status;
	}

	public Double getKpi() {
		return KPICalculator.getKpi(getPerguntasRespondidas());
	}
	
	public String getKpiAsString(){
		return KPICalculator.getKpiAsString(getPerguntasRespondidas());
	}
	
	public PerguntasRespondidas getPerguntasRespondidas() {
		List<PerguntaRespondida> perguntasRespondidas = new ArrayList<PerguntaRespondida>();
		for (EtapaRespondida etapaRespondida : etapaRespondidas) {
			perguntasRespondidas.addAll(etapaRespondida.getPerguntasRespondidas());
		}
		return new PerguntasRespondidas(perguntasRespondidas);
	}

	public boolean isFormularioCompleto() {
		return getPerguntasRespondidas().isTodasRespondidas();
	}

	public void responderTodas(Resposta resposta) {
		getPerguntasRespondidas().responderTodas(resposta);
	}

	@Override
	public void onPersist() {
		getPerguntasRespondidas().validateOnPersist();
	}

	public void setNumeroVisita(String numeroVisita) {
		try {
			setNumeroVisita(Integer.valueOf(numeroVisita));
		} catch (Exception e) {
			throw new CampoInvalidoException("Numero da Visita", numeroVisita);
		}
	}
	
}
