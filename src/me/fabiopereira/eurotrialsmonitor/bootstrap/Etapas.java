package me.fabiopereira.eurotrialsmonitor.bootstrap;

import java.util.ArrayList;
import java.util.List;

import me.fabiopereira.eurotrialsmonitor.model.Etapa;
import me.fabiopereira.eurotrialsmonitor.model.Pergunta;

import org.springframework.stereotype.Component;

@Component
public class Etapas {

	public static final List<Etapa> ALL = new ArrayList<Etapa>();
	static {
		ALL.add(new Etapa(1, "Antes da Visita", 
				new Pergunta(1, "Agendamento da Visita com o Centro de Pesquisa Clínica."), 
				new Pergunta(2, "Obtenção de Passagens e Hospedagens."), 
				new Pergunta(3, "Utilização da ferramenta de 'Controle Geral de Monitoria'."),
				new Pergunta(4, "Seleção de Pendências Gerais.")
		));
		
		ALL.add(new Etapa(2, "Monitoria", 
				new Pergunta(1, "Reunião /Conversa com Equipe do Centro."), 
				new Pergunta(2, "Revisão de AE/SAE."),
				new Pergunta(3, "Revisão de Aderência ao Protocolo/SDV."), 
				new Pergunta(4, "Recolhimento de CRF.")
		));
		
		ALL.add(new Etapa(3, "Pós Monitoria", 
				new Pergunta(1, "Relatório de Despesas (primeira atividade após a visita)."), 
				new Pergunta(2, "Envio de CRF."), 
				new Pergunta(3, "Atualizações das Ferramentas de Trabalho (Durante a Monitoria)."), 
				new Pergunta(4, "Atualizações das Ferramentas de Trabalho (Na Pós Monitoria).")
		));
	}
	public static Etapa byNumero(Integer etapaNumero) {		
		for (Etapa etapa : ALL) {
			if (etapa.getNumero().equals(etapaNumero)) {
				return etapa;
			}
		}
		throw new IllegalArgumentException("Etapa not found " + etapaNumero);
	}	
}
