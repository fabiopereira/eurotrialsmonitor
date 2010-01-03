package me.fabiopereira.eurotrialsmonitor.model;

public class Pergunta extends PersistedModel {

	private static final long serialVersionUID = 20100103L;

	private Integer numero;
	private String descricao;

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
