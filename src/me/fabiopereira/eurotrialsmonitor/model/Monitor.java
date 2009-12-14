package me.fabiopereira.eurotrialsmonitor.model;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.apache.commons.lang.StringUtils;

import com.google.appengine.api.datastore.Blob;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Monitor implements Serializable {

	private static final long serialVersionUID = 1L;

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    String id;

	public Monitor() {
	}    
	
	public Monitor(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
}
