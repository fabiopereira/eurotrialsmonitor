package me.fabiopereira.eurotrialsmonitor.repository;

import me.fabiopereira.eurotrialsmonitor.model.Monitor;

public interface MonitorRepository {

	Monitor findByUsuario(String usuario);

	void add(Monitor monitor);
}
