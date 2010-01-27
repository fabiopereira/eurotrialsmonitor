package me.fabiopereira.eurotrialsmonitor.exception;


public class CampoInvalidoException extends EurotrialsException {

	public CampoInvalidoException(String campo, String value) {
		super(String.format("Campo [%s] inv�lido [%s]", campo, value));
	}
	
}
