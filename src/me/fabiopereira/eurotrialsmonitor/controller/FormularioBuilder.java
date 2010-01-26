package me.fabiopereira.eurotrialsmonitor.controller;

import java.util.ArrayList;
import java.util.List;

import me.fabiopereira.eurotrialsmonitor.bootstrap.Etapas;
import me.fabiopereira.eurotrialsmonitor.model.Etapa;
import me.fabiopereira.eurotrialsmonitor.model.EtapaRespondida;
import me.fabiopereira.eurotrialsmonitor.model.FormularioRespondido;
import me.fabiopereira.eurotrialsmonitor.model.Monitor;
import me.fabiopereira.eurotrialsmonitor.model.Pergunta;
import me.fabiopereira.eurotrialsmonitor.model.PerguntaRespondida;

import org.springframework.stereotype.Service;

@Service
public class FormularioBuilder {

	public FormularioRespondido build(Monitor monitor) {		
		List<Etapa> etapas = Etapas.ALL;
		FormularioRespondido formularioRespondido = new FormularioRespondido(monitor);
		formularioRespondido.setEtapaRespondidas(new ArrayList<EtapaRespondida>());

		for (Etapa etapa : etapas) {
			EtapaRespondida etapaRespondida = new EtapaRespondida(etapa);
			etapaRespondida.setPerguntasRespondidas(new ArrayList<PerguntaRespondida>());

			List<Pergunta> perguntas = etapa.getPerguntas();
			for (Pergunta pergunta : perguntas) {
				etapaRespondida.getPerguntasRespondidas().add(new PerguntaRespondida(etapaRespondida, pergunta));
			}
			formularioRespondido.getEtapaRespondidas().add(etapaRespondida);
		}

		return formularioRespondido;

	}

}
