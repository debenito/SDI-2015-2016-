package uo.sdi.bussines.impl.classes.user;

import uo.sdi.bussines.Command;
import uo.sdi.model.User;
import uo.sdi.persistence.util.Jpa;

public class RegistrarUsuario implements Command {
    private User user;

    public RegistrarUsuario(User usuario) {
	this.user = usuario;
    }

    @Override
    public Object execute() {
	// TODO Auto-generated method stub
	Jpa.getManager().persist(user);
	return user;
    }

}
