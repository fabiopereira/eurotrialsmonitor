package me.fabiopereira.eurotrialsmonitor.controller;

public class MonitorNaoEncontrado extends RuntimeException {

	public MonitorNaoEncontrado(String monitorLoginId) {
		super("Monitor n�o encontrado. Favor tentar novamente.");
	}

}
