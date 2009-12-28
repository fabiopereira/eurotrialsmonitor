package me.fabiopereira.eurotrialsmonitor.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.apache.commons.lang.StringUtils;

import com.google.appengine.api.datastore.Blob;

public class Monitors implements Serializable {	
	public static final Monitors EMPTY = new Monitors(Collections.EMPTY_LIST);
	private final Collection<Monitor> values;

	public Monitors(Collection<Monitor> values) {
		super();
		this.values = values;
	}

	public Monitor findById(String id) {
		for (Monitor monitor : values) {
			if (monitor.getId().equals(id)) {
				return monitor;
			}
		}
		return null;
	}
}
