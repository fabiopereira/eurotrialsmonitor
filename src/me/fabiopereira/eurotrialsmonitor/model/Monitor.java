package me.fabiopereira.eurotrialsmonitor.model;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Monitor extends PersistedModel {	

	private static final long serialVersionUID = 20100103L;

    @Persistent    
    String usuario;
    
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



    
	
	

}
