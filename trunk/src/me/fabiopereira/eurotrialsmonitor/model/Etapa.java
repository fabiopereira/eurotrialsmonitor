package me.fabiopereira.eurotrialsmonitor.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Etapa extends PersistedModel {

	private static final long serialVersionUID = 20100103L;

	@Persistent
	private Integer numero;
	@Persistent
	private String descricao;
	@Persistent(mappedBy = "etapa", defaultFetchGroup = "true")
	@Element(dependent = "true")
	private List<Pergunta> perguntas = new ArrayList<Pergunta>();

	public Etapa() {
	}

	public Etapa(Integer numero, String descricao, Pergunta ... perguntas) {
		this.numero = numero;
		this.descricao = descricao;
		for (Pergunta pergunta : perguntas) {
			pergunta.setEtapa(this);
		}
		this.perguntas = Arrays.asList(perguntas);		
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

	public List<Pergunta> getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(List<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}

}
