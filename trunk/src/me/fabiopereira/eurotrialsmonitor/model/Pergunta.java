package me.fabiopereira.eurotrialsmonitor.model;


public class Pergunta {

	private static final long serialVersionUID = 20100103L;

	private Integer numero;
	private String descricao;

	public Pergunta() {
	}

	public Pergunta(Integer numero, String descricao) {
		super();
		this.numero = numero;
		this.descricao = descricao;
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
