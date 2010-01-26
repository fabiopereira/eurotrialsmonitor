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
				new Pergunta(1, "Agendamento da Visita com o Centro de Pesquisa Cl�nica."), 
				new Pergunta(2, "Obten��o de Passagens e Hospedagens."), 
				new Pergunta(3, "Utiliza��o da ferramenta de 'Controle Geral de Monitoria'."),
				new Pergunta(4, "Sele��o de Pend�ncias Gerais.")
		));
		
		ALL.add(new Etapa(2, "Monitoria", 
				new Pergunta(1, "Reuni�o /Conversa com Equipe do Centro."), 
				new Pergunta(2, "Revis�o de AE/SAE."),
				new Pergunta(3, "Revis�o de Ader�ncia ao Protocolo/SDV."), 
				new Pergunta(4, "Recolhimento de CRF.")
		));
		
		ALL.add(new Etapa(3, "P�s Monitoria", 
				new Pergunta(1, "Relat�rio de Despesas (primeira atividade ap�s a visita)."), 
				new Pergunta(2, "Envio de CRF."), 
				new Pergunta(3, "Atualiza��es das Ferramentas de Trabalho (Durante a Monitoria)."), 
				new Pergunta(4, "Atualiza��es das Ferramentas de Trabalho (Na P�s Monitoria).")
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
