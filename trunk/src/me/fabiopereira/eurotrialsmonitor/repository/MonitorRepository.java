package me.fabiopereira.eurotrialsmonitor.repository;

import me.fabiopereira.eurotrialsmonitor.model.Monitor;
import me.fabiopereira.eurotrialsmonitor.model.Monitors;

public interface MonitorRepository {

	Monitor findByUsuario(String usuario);

	Monitors findAll();
	
	void add(Monitor monitor);
}
