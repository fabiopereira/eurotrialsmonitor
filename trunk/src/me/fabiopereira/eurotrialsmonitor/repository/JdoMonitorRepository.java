package me.fabiopereira.eurotrialsmonitor.repository;

import java.util.Collection;

import javax.jdo.JDOException;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import me.fabiopereira.eurotrialsmonitor.model.Monitor;
import me.fabiopereira.eurotrialsmonitor.model.Monitors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.JdoCallback;
import org.springframework.orm.jdo.JdoObjectRetrievalFailureException;
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
	public Monitor findById(String id) {
		try {
			return (Monitor) jdoTemplate.getObjectById(Monitor.class, id);
		} catch (JdoObjectRetrievalFailureException e) {
			if (e.getCause() != null && e.getCause().getClass().equals(JDOObjectNotFoundException.class)) {
				return null;
			}
			throw e;
		}
//		return findAll().findById(id);
	}
	
	public Monitors findAll() {
//		Collection<Monitor> result = (Collection<Monitor>) jdoTemplate.find(Monitor.class);
		
		Collection executeFind = jdoTemplate.executeFind(new JdoCallback() {			
			@Override
			public Object doInJdo(PersistenceManager pm) throws JDOException {
				Query query = pm.newQuery(Monitor.class);
				Object result = query.execute();
				return pmf.getPersistenceManager().detachCopyAll(result);				
			}
		});
		
		if (executeFind != null){
			return new Monitors(executeFind);
		}
		
		return Monitors.EMPTY;	
	}

	@Override
	public void add(Monitor monitor) {
		jdoTemplate.makePersistent(monitor);
	}
}
