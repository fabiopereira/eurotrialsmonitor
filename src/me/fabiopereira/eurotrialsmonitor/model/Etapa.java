package me.fabiopereira.eurotrialsmonitor.model;

import java.util.List;

public class Etapa extends PersistedModel {

	private static final long serialVersionUID = 20100103L;

	private Integer numero;
	private String descricao;
	private List<Pergunta> perguntas;

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
