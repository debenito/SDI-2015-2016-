package uo.sdi.bussines.impl.classes.user;

import uo.sdi.infraestructure.Factories;
import uo.sdi.model.User;

public class UsuarioValidacion {
	 
	  public User validacionUsuario(String nombreUsuario) {
		  return Factories.persistence.newUserDao().findByLogin(nombreUsuario);
	  }
	  
	  public User buscarUsuarioporIdentificador(long id) {
		  return Factories.persistence.newUserDao().findById(id);
	  }
}
