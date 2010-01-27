package me.fabiopereira.eurotrialsmonitor.model;

import java.util.List;

import me.fabiopereira.eurotrialsmonitor.exception.TodasPerguntasComRespostaNaoTemQuePossuirJustificativaException;


public class PerguntasRespondidas {
	private final List<PerguntaRespondida> list;

	public PerguntasRespondidas(List<PerguntaRespondida> list) {
		this.list = list;
	}
	
	public boolean isTodasRespondidas() {
		for (PerguntaRespondida perguntaRespondida : list) {
			if (perguntaRespondida.getResposta() == null) {
				return false;
			}
		}
		return true;
	}

	public double getQuantidadeRespostas(Resposta resposta) {
		double quantidade = 0;
		for (PerguntaRespondida perguntaRespondida : list) {
			if (resposta.equals(perguntaRespondida.getResposta())) {
				quantidade++;
			}
		}
		return quantidade;
	}

	public void responderTodas(Resposta resposta) {
		for (PerguntaRespondida perguntaRespondida : list) {
			perguntaRespondida.setResposta(resposta);
		}
	}
	
	public void validateOnPersist() {
		validateTodasPerguntasComRespostaNaoTemQuePossuirJustificativa();
	}
	
	private void validateTodasPerguntasComRespostaNaoTemQuePossuirJustificativa() {
		for (PerguntaRespondida perguntaRespondida : list) {
			if (perguntaRespondida.isNao() && !perguntaRespondida.isJustificativaPreenchida()) {
				throw new TodasPerguntasComRespostaNaoTemQuePossuirJustificativaException();
			}
		}
	}	
}
