package uo.sdi.bussines.impl;

import uo.sdi.bussines.UsuarioService;
import uo.sdi.bussines.impl.classes.user.UsuarioAlta;
import uo.sdi.bussines.impl.classes.user.UsuarioModificacion;
import uo.sdi.bussines.impl.classes.user.UsuarioValidacion;
import uo.sdi.model.User;

public class SimpleUsuarioService implements UsuarioService {

    @Override
    public User modificar(User usuario) {
	new UsuarioModificacion().modificar(usuario);
	return usuario;
    }

    @Override
    public String comprobacionContraseñaVieja(User usuario) {
	return new UsuarioValidacion().buscarUsuarioporIdentificador(usuario.getId()).getPassword();
    }

    @Override
    public String registrarUsuario(User usuario) {
    	if(validacionUsuario(usuario.getLogin(), usuario.getPassword())==null){
    		new UsuarioAlta().registrarUsuario(usuario);
    		return "exito";
    	}
    	return "fracaso";
    }

    @Override
    public User validacionUsuario(String nombreUsuario, String pass) {
	User userByLogin = new UsuarioValidacion().validacionUsuario(nombreUsuario);
	if (userByLogin!= null && userByLogin.getLogin().equals(nombreUsuario)
		&& userByLogin.getPassword().equals(pass) )
	    return userByLogin;
	return null;
    }

    @Override
    public User buscarUsuarioporIdentificador(long id) {

	return new UsuarioValidacion().buscarUsuarioporIdentificador(id);
    }

}
