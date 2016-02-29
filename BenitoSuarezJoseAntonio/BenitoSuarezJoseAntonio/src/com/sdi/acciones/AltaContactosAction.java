package com.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Contacto;
import com.sdi.persistence.ContactoDao;
import com.sdi.persistence.exception.AlreadyPersistedException;
import com.sdi.persistence.exception.PersistenceException;

public class AltaContactosAction extends ControlAbstract {
    private ContactoDao contacto;

    @Override
    String executeAccion(HttpServletRequest request,
	    HttpServletResponse response) {
	String apellidos = request.getParameter("apellidos");
	String nombre = request.getParameter("nombre");
	String email = request.getParameter("email");
	String direccion = request.getParameter("direccion");
	String ciudad = request.getParameter("ciudad");
	String pasa = "EXITO";
	if (email != null) {
	    Contacto u = new Contacto();
	    u.setDireccion(direccion);
	    u.setApellidos(apellidos);
	    u.setEmail(email);
	    u.setCiudad(ciudad);
	    u.setNombre(nombre);
	    u.setAgenda_Usuario("admin");

	    if (u != null) {
		try {

		    contacto = Factories.persistence.createContactoDao();
		    contacto.setConection(con);
		    contacto.save(u);
		    Log.debug("Alta de [%s] correto", u.getNombre());
		    request.setAttribute("exito",
			    "Alta del usuario con nombre " + u.getNombre());
		} catch (PersistenceException e) {
		    // TODO Auto-generated catch block
		    Log.info("Problemas al guardar usuario", u.getNombre());
		    request.setAttribute("error",
			    "Problemas al guardar el usuario");
		    pasa = "FRACASO";
		    e.printStackTrace();
		} catch (AlreadyPersistedException e) {
		    Log.info("Problemas al guardar usuario", u.getNombre());
		    request.setAttribute("error",
			    "Problemas al guardar el usuario");
		    pasa = "FRACASO";
		    e.printStackTrace();
		}
	    }

	} else {
	    Log.info("No se ha introducido el email es obligatorio", email);
	    request.setAttribute("error",
		    "No se ha introducido el email es obligatorio");
	    pasa = "FRACASO";
	}

	return pasa;
    }

    @Override
    public String control(HttpServletRequest request,
	    HttpServletResponse respones) {
	// TODO Auto-generated method stub
	return null;
    }

}
