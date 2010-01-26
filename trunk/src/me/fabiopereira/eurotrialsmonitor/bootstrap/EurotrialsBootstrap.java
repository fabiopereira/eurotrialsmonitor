package me.fabiopereira.eurotrialsmonitor.bootstrap;


import me.fabiopereira.eurotrialsmonitor.model.Monitor;
import me.fabiopereira.eurotrialsmonitor.repository.MonitorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EurotrialsBootstrap {

	private final MonitorRepository monitorRepository;
	
	@Autowired
	public EurotrialsBootstrap(MonitorRepository monitorRepository) {
		this.monitorRepository = monitorRepository;
	}

	public void bootstrapAll() {
		createUser();
	}
	
	private void createUser() {
		Monitor monitor = new Monitor();
		monitor.setUsuario("anderson.porto");
		monitorRepository.add(monitor);
	}
}
