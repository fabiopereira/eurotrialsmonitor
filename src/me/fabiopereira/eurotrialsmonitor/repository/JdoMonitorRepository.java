package me.fabiopereira.eurotrialsmonitor.repository;

import java.util.Collection;

import javax.jdo.JDOException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import me.fabiopereira.eurotrialsmonitor.model.Monitor;
import me.fabiopereira.eurotrialsmonitor.model.Monitors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.JdoCallback;
import org.springframework.orm.jdo.JdoTemplate;
import org.springframework.stereotype.Repository;

//Repository
public class JdoMonitorRepository implements MonitorRepository {
	private final PersistenceManagerFactory pmf;
	JdoTemplate jdoTemplate;

	@Autowired
	public JdoMonitorRepository(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
		this.jdoTemplate = new JdoTemplate(pmf);
	}

	@Override
	public Monitor findById(String id) {
		return findAll().findById(id);
	}
	
	public Monitors findAll() {
		//return new Monitors((Collection<Monitor>) jdoTemplate.find(Monitor.class));
		return (Monitors) jdoTemplate.executeFind(new JdoCallback() {			
			@Override
			public Object doInJdo(PersistenceManager pm) throws JDOException {
				Query query = pm.newQuery(Monitor.class);
				return new Monitors((Collection<Monitor>) query.execute());				
			}
		});		
	}

	@Override
	public void add(Monitor monitor) {
		jdoTemplate.makePersistent(monitor);
	}
}
