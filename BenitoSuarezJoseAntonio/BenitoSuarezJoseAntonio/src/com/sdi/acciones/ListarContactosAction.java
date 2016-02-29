package com.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Contacto;
import com.sdi.persistence.ContactoDao;

public class ListarContactosAction extends ControlAbstract {
    private ContactoDao co;

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
	List<Contacto> usuarios;
	String pasa = "EXITO";

	try {
	    co = Factories.persistence.createContactoDao();
	    co.setConection(con);

	    usuarios = co.getAdminContactos();
	    request.setAttribute("listaContactos", usuarios);
	    Log.debug("Obtenida lista de contactos conteniendo [%d] usuarios",
		    usuarios.size());

	} catch (Exception e) {
	    Log.error("Algo ha ocurrido obteniendo lista de usuarios");
	    request.setAttribute("error",
		    "Algo ha ocurrido obteniendo lista de contactos");
	    pasa = "FRACASO";
	}
	return pasa;

    }

}
