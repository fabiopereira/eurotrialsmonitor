package me.fabiopereira.eurotrialsmonitor.repository;

import java.util.List;

import javax.jdo.PersistenceManagerFactory;

import me.fabiopereira.eurotrialsmonitor.model.Etapa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.JdoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdoEtapaRepository implements EtapaRepository {
	private final PersistenceManagerFactory pmf;
	JdoTemplate jdoTemplate;

	@Autowired
	public JdoEtapaRepository(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
		this.jdoTemplate = new JdoTemplate(pmf);
	}

	@Override
	public void add(Etapa etapa) {
		jdoTemplate.makePersistent(etapa);
	}

	@Override
	public List<Etapa> findAll() {
		return (List<Etapa>) jdoTemplate.find(Etapa.class);
	}
}
