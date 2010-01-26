package me.fabiopereira.eurotrialsmonitor.model;

import java.util.List;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import me.fabiopereira.eurotrialsmonitor.bootstrap.Etapas;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class EtapaRespondida extends PersistedModel {

	private static final long serialVersionUID = 20100103L;

	@Persistent
	private Integer etapaNumero;
	@Persistent
	private String comentario;
	@Persistent
	private List<PerguntaRespondida> perguntasRespondidas;

	public EtapaRespondida() {
	}

	public EtapaRespondida(Etapa etapa) {
		this.etapaNumero = etapa.getNumero();
	}

	public List<PerguntaRespondida> getPerguntasRespondidas() {
		return perguntasRespondidas;
	}

	public void setPerguntasRespondidas(List<PerguntaRespondida> perguntasRespondidas) {
		this.perguntasRespondidas = perguntasRespondidas;
	}

	public Integer getEtapaNumero() {
		return etapaNumero;
	}

	public void setEtapaNumero(Integer etapaNumero) {
		this.etapaNumero = etapaNumero;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Etapa getEtapa() {
		return Etapas.byNumero(etapaNumero);
	}
	
	public Double getKpi() {
		return KPICalculator.getKpi(new PerguntasRespondidas(getPerguntasRespondidas()));
	}	
	
	public String getKpiAsString(){
		return KPICalculator.getKpiAsString(new PerguntasRespondidas(getPerguntasRespondidas()));
	}
}
