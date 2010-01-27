package me.fabiopereira.eurotrialsmonitor.exception;


public class CampoObrigatorioException extends EurotrialsException {

	public CampoObrigatorioException() {
		super("Favor preencher todos os campos obrigatórios (*) antes de salvar.");
	}
	
}
