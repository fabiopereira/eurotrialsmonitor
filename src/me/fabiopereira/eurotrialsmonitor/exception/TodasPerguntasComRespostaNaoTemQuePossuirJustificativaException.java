package me.fabiopereira.eurotrialsmonitor.exception;


public class TodasPerguntasComRespostaNaoTemQuePossuirJustificativaException extends EurotrialsException {
	public TodasPerguntasComRespostaNaoTemQuePossuirJustificativaException() {
		super("Favor adicionar justificativa para todas as perguntas com resposta 'Não'");
	}
}
