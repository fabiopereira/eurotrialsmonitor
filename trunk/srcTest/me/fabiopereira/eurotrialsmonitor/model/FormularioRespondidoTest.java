package me.fabiopereira.eurotrialsmonitor.model;

import static org.junit.Assert.assertEquals;
import me.fabiopereira.eurotrialsmonitor.controller.FormularioBuilder;

import org.junit.Test;

public class FormularioRespondidoTest {

	@Test
	public void getKpi100() throws Exception {
		FormularioRespondido formularioRespondido = new FormularioBuilder().build(new Monitor());
		formularioRespondido.responderTodas(Resposta.SIM);
		assertEquals(100d, formularioRespondido.getKpi(), 2);
	}

	@Test
	public void getKpi0() throws Exception {
		FormularioRespondido formularioRespondido = new FormularioBuilder().build(new Monitor());
		formularioRespondido.responderTodas(Resposta.NAO);
		assertEquals(0d, formularioRespondido.getKpi(), 2);
	}
	
}
