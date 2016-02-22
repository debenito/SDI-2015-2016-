package uo.sdi.acciones.impl;

import uo.sdi.acciones.logic.UsuarioLogica;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.UserDao;

public class UsuarioLogic implements UsuarioLogica {
   private UserDao dao = PersistenceFactory.newUserDao();
    @Override
    public User modificar(User usuario,String email,String login,String nombre,String passNueva,String apellidos) {
	usuario.setEmail(email);
	usuario.setLogin(login);
	usuario.setName(nombre);
	usuario.setPassword(passNueva);
	usuario.setSurname(apellidos);
	dao.update(usuario);
	return usuario;
    }
    @Override
    public String comprobacionContraseñaVieja(User usuario) {
	User u = dao.findById(usuario.getId());
	return u.getPassword();	
    }
    @Override
    public void registrarUsuario(User usuario,String login,String nombre,String apellidos, String pass1,String nuevoEmail) {
	  usuario = new User();
	    usuario.setLogin(login);
	    usuario.setName(nombre);
	    usuario.setSurname(apellidos);
	    usuario.setPassword(pass1);
	    usuario.setEmail(nuevoEmail);
	    usuario.setStatus(UserStatus.ACTIVE);
	    dao.save(usuario);
	    
    }
    @Override
    public User validacionUsuario(String nombreUsuario,String pass) {
	User userByLogin = dao.findByLogin(nombreUsuario);
	if( userByLogin.getLogin().equals(nombreUsuario) && userByLogin.getPassword().equals(pass))
	    return userByLogin;
	return null;
    }
    @Override
    public User buscarUsuarioporIdentificador(long id) {

	return dao.findById(id);
    }

    
}
