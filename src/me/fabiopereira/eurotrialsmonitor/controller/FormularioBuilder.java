package me.fabiopereira.eurotrialsmonitor.controller;

import java.util.ArrayList;
import java.util.List;

import me.fabiopereira.eurotrialsmonitor.model.Etapa;
import me.fabiopereira.eurotrialsmonitor.model.EtapaRespondida;
import me.fabiopereira.eurotrialsmonitor.model.FormularioRespondido;
import me.fabiopereira.eurotrialsmonitor.model.Pergunta;
import me.fabiopereira.eurotrialsmonitor.model.PerguntaRespondida;
import me.fabiopereira.eurotrialsmonitor.repository.EtapaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormularioBuilder {

	private final EtapaRepository etapaRepository;

	@Autowired
	public FormularioBuilder(EtapaRepository etapaRepository) {
		super();
		this.etapaRepository = etapaRepository;
	}

	public FormularioRespondido build() {

		List<Etapa> etapas = etapaRepository.findAll();

		FormularioRespondido formularioRespondido = new FormularioRespondido();
		formularioRespondido.setEtapaRespondidas(new ArrayList<EtapaRespondida>());

		for (Etapa etapa : etapas) {
			EtapaRespondida etapaRespondida = new EtapaRespondida(formularioRespondido, etapa);
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
