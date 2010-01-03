package me.fabiopereira.eurotrialsmonitor.repository;

import java.util.Collection;
import java.util.List;

import javax.jdo.JDOException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import me.fabiopereira.eurotrialsmonitor.model.Monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.JdoCallback;
import org.springframework.orm.jdo.JdoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdoMonitorRepository implements MonitorRepository {
	private final PersistenceManagerFactory pmf;
	JdoTemplate jdoTemplate;

	@Autowired
	public JdoMonitorRepository(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
		this.jdoTemplate = new JdoTemplate(pmf);
	}

	@Override
	public Monitor findByUsuario(String user) {
		Query query = pmf.getPersistenceManager().newQuery(Monitor.class);
		query.setFilter("usuario == usuarioParam");
		query.declareParameters("String usuarioParam");

		try {
			List<Monitor> monitors = (List<Monitor>) query.execute(user);
			if (monitors == null || monitors.isEmpty()){
				return null;
			}
			return monitors.get(0);
		} finally {
			query.closeAll();
		}
	}

	@Override
	public void add(Monitor monitor) {
		jdoTemplate.makePersistent(monitor);
	}
}
