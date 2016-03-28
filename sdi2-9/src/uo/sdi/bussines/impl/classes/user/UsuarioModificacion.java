package uo.sdi.bussines.impl.classes.user;

import uo.sdi.infraestructure.Factories;
import uo.sdi.model.User;

public class UsuarioModificacion {
	public void modificar(User usuario) {
		Factories.persistence.newUserDao().update(usuario);
	}
}
