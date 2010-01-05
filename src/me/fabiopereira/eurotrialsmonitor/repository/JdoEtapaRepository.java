package me.fabiopereira.eurotrialsmonitor.repository;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

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
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.currentTransaction().begin();
		try {
		    pm.makePersistent(etapa);
		    pm.currentTransaction().commit();
		} finally {
		    if (pm.currentTransaction().isActive()) {
		        pm.currentTransaction().rollback();
		    }
		}
		
//		jdoTemplate.makePersistent(etapa);
	}

	@Override
	public List<Etapa> findAll() {
		Query query = pmf.getPersistenceManager().newQuery(Etapa.class);

		try {
			List<Etapa> etapas = (List<Etapa>) query.execute();
			
			return etapas;
		} finally {
			query.closeAll();
		}
	}
}
