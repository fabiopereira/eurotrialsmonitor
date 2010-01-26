package me.fabiopereira.eurotrialsmonitor.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Etapa {

	private static final long serialVersionUID = 20100103L;

	private Integer numero;
	private String descricao;
	private List<Pergunta> perguntas = new ArrayList<Pergunta>();

	public Etapa() {
	}

	public Etapa(Integer numero, String descricao, Pergunta ... perguntas) {
		this.numero = numero;
		this.descricao = descricao;
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
	
	public Pergunta getPerguntaByNumero(Integer perguntaNumero) {
		for (Pergunta pergunta : perguntas) {
			if (pergunta.getNumero().equals(perguntaNumero)) {
				return pergunta;
			}
		}
		throw new IllegalArgumentException("Pergunta not found " + perguntaNumero);
	}
}
