package me.fabiopereira.eurotrialsmonitor.model;

import java.util.List;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class EtapaRespondida extends PersistedModel {

	private static final long serialVersionUID = 20100103L;

	@Persistent
	private Etapa etapa;
	@Persistent
	private String comentario;
	@Persistent(mappedBy = "etapaRespondida")
	private List<PerguntaRespondida> perguntasRespondidas;
	@Persistent
	private FormularioRespondido formularioRespondido;

	public EtapaRespondida() {
	}

	public EtapaRespondida(FormularioRespondido formularioRespondido, Etapa etapa) {
		this.formularioRespondido = formularioRespondido;
		this.etapa = etapa;
	}

	public FormularioRespondido getFormularioRespondido() {
		return formularioRespondido;
	}

	public void setFormularioRespondido(FormularioRespondido formularioRespondido) {
		this.formularioRespondido = formularioRespondido;
	}

	public List<PerguntaRespondida> getPerguntasRespondidas() {
		return perguntasRespondidas;
	}

	public void setPerguntasRespondidas(List<PerguntaRespondida> perguntasRespondidas) {
		this.perguntasRespondidas = perguntasRespondidas;
	}

	public Etapa getEtapa() {
		return etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}
