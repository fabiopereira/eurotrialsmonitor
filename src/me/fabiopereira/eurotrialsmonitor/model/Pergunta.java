package me.fabiopereira.eurotrialsmonitor.model;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Pergunta extends PersistedModel {

	private static final long serialVersionUID = 20100103L;

	@Persistent
	private Integer numero;
	@Persistent
	private String descricao;
	@Persistent
	private Etapa etapa;

	public Pergunta() {
	}

	public Pergunta(Integer numero, String descricao) {
		super();
		this.numero = numero;
		this.descricao = descricao;
	}

	public Etapa getEtapa() {
		return etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
