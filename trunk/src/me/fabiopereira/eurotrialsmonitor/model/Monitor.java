package me.fabiopereira.eurotrialsmonitor.model;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import me.fabiopereira.eurotrialsmonitor.exception.EurotrialsException;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Monitor extends PersistedModel {	

	private static final long serialVersionUID = 20100103L;

	public static final String ADMIN_USUARIO = "admin";

    @Persistent    
    String usuario;
    
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public boolean isAdmin() {
		return ADMIN_USUARIO.equals(getUsuario());
	}

	public void makeSureIsNotAdmin() {
		if (isAdmin()) {
			throw new EurotrialsException("Monitor admin não pode criar ou atualizar formulários, favor usar um monitor válido");
		}
	}
}
