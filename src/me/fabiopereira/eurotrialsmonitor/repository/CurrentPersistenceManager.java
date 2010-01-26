package me.fabiopereira.eurotrialsmonitor.repository;

import javax.jdo.PersistenceManager;

public class CurrentPersistenceManager {
	private static final ThreadLocal<PersistenceManager> threadLocal = new ThreadLocal<PersistenceManager>();

	public static void set(PersistenceManager pm) {
		threadLocal.set(pm);
	}

	public static PersistenceManager get() {
		return threadLocal.get();
	}

	public static void close() {
		if (get() != null) {
			get().close();
		}
	}
}
