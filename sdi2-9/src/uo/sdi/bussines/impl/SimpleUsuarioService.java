package uo.sdi.bussines.impl;

import uo.sdi.bussines.CommandExecutor;
import uo.sdi.bussines.UsuarioService;
import uo.sdi.bussines.impl.classes.user.ModificarUsuario;
import uo.sdi.bussines.impl.classes.user.RegistrarUsuario;
import uo.sdi.bussines.impl.classes.user.UsuarioValidacion;
import uo.sdi.model.User;

public class SimpleUsuarioService implements UsuarioService {
    CommandExecutor executor = new CommandExecutor();

    @Override
    public User modificar(User usuario) {
	return (User) executor.execute(new ModificarUsuario(usuario));
    }

    @Override
    public String comprobacionContraseñaVieja(User usuario) {
	return new UsuarioValidacion().buscarUsuarioporIdentificador(
		usuario.getId()).getPassword();
    }

    @Override
    public String registrarUsuario(User usuario) {
	if (validacionUsuario(usuario.getLogin(), usuario.getPassword()) == null) {
	    executor.execute(new RegistrarUsuario(usuario));
	    return "exito";
	}
	return "fracaso";
    }

    @Override
    public User validacionUsuario(String nombreUsuario, String pass) {
	User userByLogin = new UsuarioValidacion()
		.validacionUsuario(nombreUsuario);
	if (userByLogin != null && userByLogin.getLogin().equals(nombreUsuario)
		&& userByLogin.getPassword().equals(pass))
	    return userByLogin;
	return null;
    }

    @Override
    public User buscarUsuarioporIdentificador(long id) {

	return new UsuarioValidacion().buscarUsuarioporIdentificador(id);
    }

}
