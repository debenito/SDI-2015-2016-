package uo.sdi.bussines.impl.classes.user;

import uo.sdi.infraestructure.Factories;
import uo.sdi.model.User;

public class UsuarioAlta {
    public void registrarUsuario(User usuario) {
	Factories.persistence.newUserDao().save(usuario);
    }
}
