package me.fabiopereira.eurotrialsmonitor.repository;

import me.fabiopereira.eurotrialsmonitor.model.EtapaRespondida;
import me.fabiopereira.eurotrialsmonitor.model.FormularioRespondido;
import me.fabiopereira.eurotrialsmonitor.model.PerguntaRespondida;

import org.springframework.stereotype.Repository;

@Repository
public class FormularioRespondidoRepository extends AbstractRepository<FormularioRespondido> {
	
	@Override
	protected void loadLazy(FormularioRespondido formularioRespondido) {
		System.out.println("Monitor " + formularioRespondido.getMonitor());
		for (EtapaRespondida etapaRespondida : formularioRespondido.getEtapaRespondidas()) {
			for (PerguntaRespondida perguntaRespondida : etapaRespondida.getPerguntasRespondidas()) {
				System.out.println("Pergunta " + perguntaRespondida.getPerguntaNumero());
			}
		}
	}
	
}
