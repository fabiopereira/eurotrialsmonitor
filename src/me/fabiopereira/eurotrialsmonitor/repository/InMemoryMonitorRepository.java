package me.fabiopereira.eurotrialsmonitor.repository;

import java.util.HashMap;
import java.util.Map;

import me.fabiopereira.eurotrialsmonitor.model.Monitor;
import me.fabiopereira.eurotrialsmonitor.model.Monitors;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryMonitorRepository implements MonitorRepository {
	
	Map<String, Monitor> map = new HashMap<String, Monitor>();
	
	public InMemoryMonitorRepository() {
		addMonitor("anderson.porto");
		addMonitor("fabio.pereira");
	}

	private void addMonitor(String id) {
		map.put(id, new Monitor(id));
	}

	@Override
	public Monitor findById(String id) {
		return map.get(id);
	}

	public Monitors findAll() {
		return null;
	}

	@Override
	public void add(Monitor monitor) {
		map.put(monitor.getId(), monitor);
	}
}
