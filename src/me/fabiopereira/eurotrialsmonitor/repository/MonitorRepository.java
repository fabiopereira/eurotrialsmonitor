package me.fabiopereira.eurotrialsmonitor.repository;

import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import me.fabiopereira.eurotrialsmonitor.model.Monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MonitorRepository extends AbstractRepository<Monitor> {

	@Autowired
	public MonitorRepository(PersistenceManagerFactory pmf) {
		super(pmf);
	}

	public Monitor findByUsuario(String user) {
		Query query = pmf.getPersistenceManager().newQuery(Monitor.class);
		query.setFilter("usuario == usuarioParam");
		query.declareParameters("String usuarioParam");

		return findByQuery(query, user);
	}

}
