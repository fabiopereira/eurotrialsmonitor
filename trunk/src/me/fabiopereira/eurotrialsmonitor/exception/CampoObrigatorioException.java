package me.fabiopereira.eurotrialsmonitor.exception;


public class CampoObrigatorioException extends EurotrialsException {

	public CampoObrigatorioException() {
		super("Favor preencher todos os campos obrigat�rios (*) antes de salvar.");
	}
	
}
