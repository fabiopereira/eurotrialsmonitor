package me.fabiopereira.eurotrialsmonitor.repository;

import javax.jdo.PersistenceManagerFactory;

import me.fabiopereira.eurotrialsmonitor.model.Etapa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EtapaRepository extends AbstractRepository<Etapa> {

	@Autowired
	public EtapaRepository(PersistenceManagerFactory pmf) {
		super(pmf);
	}

}
