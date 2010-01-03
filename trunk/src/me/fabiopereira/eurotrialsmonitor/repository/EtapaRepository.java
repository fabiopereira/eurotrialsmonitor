package me.fabiopereira.eurotrialsmonitor.repository;

import java.util.List;

import me.fabiopereira.eurotrialsmonitor.model.Etapa;

public interface EtapaRepository {

	void add(Etapa etapa);

	List<Etapa> findAll();
}
