package me.fabiopereira.eurotrialsmonitor.bootstrap;

import me.fabiopereira.eurotrialsmonitor.model.Etapa;
import me.fabiopereira.eurotrialsmonitor.model.Monitor;
import me.fabiopereira.eurotrialsmonitor.model.Pergunta;
import me.fabiopereira.eurotrialsmonitor.repository.EtapaRepository;
import me.fabiopereira.eurotrialsmonitor.repository.MonitorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EurotrialsBootstrap {

	private final MonitorRepository monitorRepository;
	private final EtapaRepository etapaRepository;

	@Autowired
	public EurotrialsBootstrap(MonitorRepository monitorRepository, 
			EtapaRepository etapaRepository) {
		this.monitorRepository = monitorRepository;
		this.etapaRepository = etapaRepository;		
		bootstrapAll();
	}

	private void bootstrapAll() {
		createUser();		
		createEtapasAndPerguntas();
	}

	private void createEtapasAndPerguntas() {
		add(new Etapa(
					1, "Antes da Visita",
					new Pergunta(1, "Agendamento da Visita com o Centro de Pesquisa Cl�nica."),
					new Pergunta(2, "Obten��o de Passagens e Hospedagens."),
					new Pergunta(3, "Utiliza��o da ferramenta de 'Controle Geral de Monitoria'."),
					new Pergunta(4, "Sele��o de Pend�ncias Gerais.")
//					new Pergunta(5, ""),
//					new Pergunta(6, ""),
//					new Pergunta(7, ""),
//					new Pergunta(8, ""),
//					new Pergunta(9, ""),
//					new Pergunta(10, ""),
//					new Pergunta(11, ""),
//					new Pergunta(12, ""),
//					new Pergunta(13, ""),
//					new Pergunta(14, ""),
				));
		
		add(new Etapa(
				2, "Monitoria",
				new Pergunta(1, "Reuni�o /Conversa com Equipe do Centro."),
				new Pergunta(2, "Revis�o de AE/SAE."),
				new Pergunta(3, "Revis�o de Ader�ncia ao Protocolo/SDV."),
				new Pergunta(4, "Recolhimento de CRF.")
			));
		add(new Etapa(
				3, "P�s Monitoria",
				new Pergunta(1, "Relat�rio de Despesas (primeira atividade ap�s a visita)."),
				new Pergunta(2, "Envio de CRF."),
				new Pergunta(3, "Atualiza��es das Ferramentas de Trabalho (Durante a Monitoria)."),
				new Pergunta(4, "Atualiza��es das Ferramentas de Trabalho (Na P�s Monitoria).")
			));
		
		
	}

	private void add(Etapa etapa) {
		etapaRepository.add(etapa);
	}

	private void createUser() {
		Monitor monitor = new Monitor();		
		monitor.setUsuario("anderson.porto");
		monitorRepository.add(monitor);
	}	
}
