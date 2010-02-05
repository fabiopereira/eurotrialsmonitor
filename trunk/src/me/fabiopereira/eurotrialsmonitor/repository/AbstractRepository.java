package me.fabiopereira.eurotrialsmonitor.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import me.fabiopereira.eurotrialsmonitor.model.PersistedModel;

import com.google.appengine.api.datastore.Key;

public abstract class AbstractRepository<T extends PersistedModel> {

	public T persist(final T entity) {
		entity.onPersist();
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
	
	public T findByPrimaryKey(Key key) {
		T entity = (T) pm().getObjectById(getEntityClass(), key);
		loadLazy(entity);
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
	
	protected List<T> findListByQuery(Query query, Object param) {
		try {
			List<T> entities = (List<T>) query.execute(param);
			pm().detachCopyAll(entities);
			return entities;
		} finally {
			query.closeAll();
		}
	}

	protected List<T> findListByQuery(Query query, Object param, Object param2) {
		try {
			List<T> entities = (List<T>) query.execute(param, param2);
			pm().detachCopyAll(entities);
			return entities;
		} finally {
			query.closeAll();
		}
	}

	protected List<T> findListByQuery(Query query, Object param, Object param2, Object param3) {
		try {
			List<T> entities = (List<T>) query.execute(param, param2, param3);
			pm().detachCopyAll(entities);
			return entities;
		} finally {
			query.closeAll();
		}
	}

	
	public List<T> findAll() {
		Query query = pm().newQuery(getEntityClass());
		try {
			List<T> entities = (List<T>) query.execute();
			for (T entity : entities) {
				loadLazy(entity);
			}
			pm().detachCopyAll(entities);
			return entities;
		} finally {
			query.closeAll();
		}
	}

	protected void loadLazy(T entity) {
	}

	private Class getEntityClass() {
		return ((Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}

}
