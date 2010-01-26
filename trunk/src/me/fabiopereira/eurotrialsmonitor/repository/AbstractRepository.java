package me.fabiopereira.eurotrialsmonitor.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public abstract class AbstractRepository<T> {

	public T add(final T entity) {
		pm().currentTransaction().begin();
		try {
			pm().makePersistent(entity);
			pm().currentTransaction().commit();
		} finally {
			if (pm().currentTransaction().isActive()) {
				pm().currentTransaction().rollback();
			}
		}
		return entity;
	}

	protected PersistenceManager pm() {
		return CurrentPersistenceManager.get();
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
		Query query = pm().newQuery(getEntityClass());
		try {
			List<T> entities = (List<T>) query.execute();
			// pm.detachCopyAll(entities);
			return entities;
		} finally {
			query.closeAll();
		}
	}

	private Class getEntityClass() {
		return ((Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}

}
