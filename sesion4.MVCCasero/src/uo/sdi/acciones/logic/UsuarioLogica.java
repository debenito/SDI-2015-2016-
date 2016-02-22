package uo.sdi.acciones.logic;

import uo.sdi.model.User;

public interface UsuarioLogica {

    User modificar(User usuario, String email, String login, String nombre,
	    String passNueva, String apellidos);

    String comprobacionContraseñaVieja(User usuario);
    
    void registrarUsuario(User usuario,String login,String nombre,String apellidos, String pass1,String nuevoEmail);
    
    User validacionUsuario(String nombreUsuario, String pass);
    

    User buscarUsuarioporIdentificador(long id);
}
