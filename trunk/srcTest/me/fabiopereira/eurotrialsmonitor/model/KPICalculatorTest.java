package me.fabiopereira.eurotrialsmonitor.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class KPICalculatorTest {

	@Test
	public void getKpi100() throws Exception {
		assertEquals(100d, getKpi(Resposta.SIM, Resposta.SIM, Resposta.SIM), 2);
	}

	@Test
	public void getKpi0() throws Exception {
		assertEquals(0d, getKpi(Resposta.NAO, Resposta.NAO, Resposta.NAO), 2);
	}
	
	@Test
	public void somenteRespostasSimContamParaOKPI() throws Exception {
		assertEquals(50d, getKpi(Resposta.NAO, Resposta.NAO, Resposta.SIM, Resposta.SIM, Resposta.NA), 2);
	}
	
	@Test
	public void perguntasNaoRespondidasNaoGeramKPI() throws Exception {
		assertNull(getKpi(Resposta.SIM, null, Resposta.SIM));
	}
	
	private Double getKpi(Resposta ... respostas) {
		List<PerguntaRespondida> perguntasRespondidas = new ArrayList<PerguntaRespondida>();
		for (Resposta resposta : respostas) {
			PerguntaRespondida perguntaRespondida = new PerguntaRespondida();
			perguntaRespondida.setResposta(resposta);
			perguntasRespondidas.add(perguntaRespondida);
		}		
		return KPICalculator.getKpi(new PerguntasRespondidas(perguntasRespondidas));
	}

	
}
