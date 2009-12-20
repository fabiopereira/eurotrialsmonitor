package me.fabiopereira.eurotrialsmonitor.repository;

import java.util.Collection;

import javax.jdo.JDOException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.orm.jdo.JdoCallback;

import me.fabiopereira.eurotrialsmonitor.model.Monitor;
import me.fabiopereira.eurotrialsmonitor.model.Monitors;

public interface MonitorRepository {

	Monitor findById(String id);

	Monitors findAll();
	
	void add(Monitor monitor);
}
