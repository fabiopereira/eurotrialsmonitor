package me.fabiopereira.eurotrialsmonitor.model;

public class EtapaRespondida extends PersistedModel {

	private static final long serialVersionUID = 20100103L;

	private Etapa etapa;
	private String comentario;

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
