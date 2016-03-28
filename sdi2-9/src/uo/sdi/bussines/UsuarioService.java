package uo.sdi.bussines;

import uo.sdi.model.User;

public interface UsuarioService {

    User modificar(User usuario);

    String comprobacionContraseñaVieja(User usuario);

    String registrarUsuario(User usuario);

    User validacionUsuario(String nombreUsuario, String pass);

    User buscarUsuarioporIdentificador(long id);

}
