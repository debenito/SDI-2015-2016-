package com.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Usuario;
import com.sdi.persistence.UsuarioDao;

public class ListarUsuariosAction extends ControlAbstract {
    private UsuarioDao ud;

    @Override
    public String toString() {
	return getClass().getName();
    }

    @Override
    public String control(HttpServletRequest request,
	    HttpServletResponse respones) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    String executeAccion(HttpServletRequest request,
	    HttpServletResponse response) {
	HttpSession session = request.getSession();
	List<Usuario> usuarios;

	try {
	    ud = Factories.persistence.createUsuarioDao();
	    ud.setConection(con);
	    usuarios = ud.getUsuarios();
	    session.setAttribute("listaUsuarios", usuarios);
	    Log.debug("Obtenida lista de usuarios conteniendo [%d] usuarios",
		    usuarios.size());

	} catch (Exception e) {
	    Log.error("Algo ha ocurrido obteniendo lista de usuarios ");
	    request.setAttribute("error",
		    "Algo ha ocurrido obteniendo lista de usuarios ");

	}
	return "EXITO";

    }

}
