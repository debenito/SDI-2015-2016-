package uo.sdi.bussines.impl.classes.user;

import uo.sdi.bussines.Command;
import uo.sdi.model.User;
import uo.sdi.persistence.util.Jpa;

public class ModificarUsuario implements Command {
    private User user;

    public ModificarUsuario(User user) {
	this.user = user;
    }

    @Override
    public Object execute() {
	// TODO Auto-generated method stub
	Jpa.getManager().merge(user);
	return user;
    }

}
