package me.fabiopereira.eurotrialsmonitor.repository;

import javax.jdo.PersistenceManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.JdoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MonitorBootstrap {
	private final PersistenceManagerFactory pmf;
	JdoTemplate jdoTemplate;

	@Autowired
	public MonitorBootstrap(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
		this.jdoTemplate = new JdoTemplate(pmf);
		createAll();
	}

	private void createAll() {
//		Monitor monitor = new Monitor();
//		monitor.setId("anderson.porto");
//		jdoTemplate.makePersistent(monitor);
	}
}
