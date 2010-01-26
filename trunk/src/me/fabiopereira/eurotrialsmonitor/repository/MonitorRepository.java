package me.fabiopereira.eurotrialsmonitor.repository;

import javax.jdo.Query;

import me.fabiopereira.eurotrialsmonitor.model.Monitor;

import org.springframework.stereotype.Repository;

@Repository
public class MonitorRepository extends AbstractRepository<Monitor> {

	public Monitor findByUsuario(final String user) {
		Query query = pm().newQuery(Monitor.class);
		query.setFilter("usuario == usuarioParam");
		query.declareParameters("String usuarioParam");
		return findByQuery(query, user);
	}
}
