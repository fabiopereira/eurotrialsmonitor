package me.fabiopereira.eurotrialsmonitor.repository;

import java.util.Date;

import javax.jdo.Query;

import me.fabiopereira.eurotrialsmonitor.exception.EurotrialsException;
import me.fabiopereira.eurotrialsmonitor.model.EtapaRespondida;
import me.fabiopereira.eurotrialsmonitor.model.FormularioRespondido;
import me.fabiopereira.eurotrialsmonitor.model.FormulariosRespondidos;
import me.fabiopereira.eurotrialsmonitor.model.PerguntaRespondida;

import org.springframework.stereotype.Repository;

@Repository
public class FormularioRespondidoRepository extends AbstractRepository<FormularioRespondido> {

	@Override
	protected void loadLazy(FormularioRespondido formularioRespondido) {
		formularioRespondido.getMonitor();
		for (EtapaRespondida etapaRespondida : formularioRespondido.getEtapaRespondidas()) {
			for (PerguntaRespondida perguntaRespondida : etapaRespondida.getPerguntasRespondidas()) {
				perguntaRespondida.getPerguntaNumero();
			}
		}
	}
	
	public FormulariosRespondidos findByMonitorAndEntreDatasVisita(final String monitorUsuarioParam, final Date
			startDate, final Date endDate) {
		FormulariosRespondidos formulariosRespondidos = null;
		if (startDate != null || endDate != null) {
			formulariosRespondidos = findEntreDatasVisita(startDate, endDate);			
		} else {
			formulariosRespondidos = new FormulariosRespondidos(findAll());
		}
		
		return formulariosRespondidos.filterByUsuario(monitorUsuarioParam);
	}
	
	public FormulariosRespondidos findEntreDatasVisita(final Date startDate, final Date endDate) {
		if (startDate == null || endDate == null) {
			throw new EurotrialsException("Favor preencher o intervalo completo de datas");
		}
		Query query = pm().newQuery(FormularioRespondido.class);
		query.setFilter("dataVisita >= startDate && dataVisita <= endDate");
		query.declareParameters("java.util.Date startDate,  java.util.Date endDate");
		return new FormulariosRespondidos(findListByQuery(query, startDate, endDate));
	}

}
