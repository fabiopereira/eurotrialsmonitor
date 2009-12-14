package me.fabiopereira.eurotrialsmonitor.repository;

import me.fabiopereira.eurotrialsmonitor.model.Monitor;

public interface MonitorRepository {

	Monitor findById(String id);
}
