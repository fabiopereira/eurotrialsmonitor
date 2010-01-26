package me.fabiopereira.eurotrialsmonitor.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.springframework.orm.jdo.JdoTemplate;

public abstract class AbstractRepository<T> {

	JdoTemplate jdoTemplate;
	PersistenceManagerFactory pmf;

	protected AbstractRepository(PersistenceManagerFactory pmf) {
		super();
		this.jdoTemplate = new JdoTemplate(pmf);
		this.pmf = pmf;
	}

	public void add(T entity) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.currentTransaction().begin();
		try {
			pm.makePersistent(entity);
			pm.currentTransaction().commit();
		} finally {
			if (pm.currentTransaction().isActive()) {
				pm.currentTransaction().rollback();
			}
		}
		// jdoTemplate.makePersistent(entity);
	}

	protected T findByQuery(Query query, String param) {
		try {
			List<T> entities = (List<T>) query.execute(param);
			if (entities == null || entities.isEmpty()) {
				return null;
			}
			return entities.get(0);
		} finally {
			query.closeAll();
		}
	}

	public List<T> findAll() {
		Query query = pmf.getPersistenceManager().newQuery(getEntityClass());

		try {
			List<T> entities = (List<T>) query.execute();
			return entities;
		} finally {
			query.closeAll();
		}
	}

	private Class getEntityClass() {
		return ((Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}
}
