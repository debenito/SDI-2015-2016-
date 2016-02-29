package com.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Contacto;
import com.sdi.model.Usuario;
import com.sdi.persistence.ContactoDao;
import com.sdi.persistence.UsuarioDao;
import com.sdi.persistence.exception.AlreadyPersistedException;
import com.sdi.persistence.exception.PersistenceException;

public class AltaContactosUsuarioAction extends ControlAbstract {
    private ContactoDao cd;
    private UsuarioDao ud;

    @Override
    public String control(HttpServletRequest request,
	    HttpServletResponse respones) {

	String pasa = "EXITO";
	ud = Factories.persistence.createUsuarioDao();
	ud.setConection(con);
	List<Usuario> lista = ud.getUsuarios();
	HttpSession session = request.getSession();
	Usuario u = (Usuario) session.getAttribute("user");
	for (Usuario usuario : lista) {
	    if (usuario.getLogin().equals(u.getLogin()) && !usuario.isActivo()) {
		pasa = "FRACASO";
		Log.info("Usuario desactivado "
			+ "por el administrador se le cerrara la session");
		request.setAttribute("error", "Usuario desactivado"
			+ "por el administrador se le cerrara la session");
	    }
	}
	return pasa;
    }

    @Override
    String executeAccion(HttpServletRequest request,
	    HttpServletResponse response) {
	String pasa = "EXITO";
	pasa = control(request, response);
	if (pasa.equals("EXITO")) {
	    HttpSession session = request.getSession();
	    Usuario user = (Usuario) session.getAttribute("user");
	    String apellidos = request.getParameter("apellidos");
	    String nombre = request.getParameter("nombre");
	    String email = request.getParameter("email");
	    String direccion = request.getParameter("direccion");
	    String ciudad = request.getParameter("ciudad");

	    if (email != null) {
		Contacto u = new Contacto();
		u.setDireccion(direccion);
		u.setApellidos(apellidos);
		u.setEmail(email);
		u.setCiudad(ciudad);
		u.setNombre(nombre);
		u.setAgenda_Usuario(user.getLogin());

		if (u != null) {
		    try {
			cd = Factories.persistence.createContactoDao();
			cd.setConection(con);
			cd.save(u);
			Log.debug("Alta de [%s] correto", u.getNombre());
			request.setAttribute("exito",
				"Alta del usuario con nombre  " + u.getNombre());
		    } catch (PersistenceException e) {
			Log.info("Problemas al guardar usuario", u.getNombre());
			request.setAttribute("error",
				"Problemas al guardar el usuario");
			pasa = "FRACASO";
			e.printStackTrace();
		    } catch (AlreadyPersistedException e) {
			Log.info("Problemas al gurar usuario", u.getNombre());
			request.setAttribute("error",
				"Problemas al guardar el usuario");
			pasa = "FRACASO";
			e.printStackTrace();
		    }
		}

	    } else {
		Log.info("No se ha introducido el email que es obligatorio",
			email);
		request.setAttribute("error", "No se ha introducido el email ");
		pasa = "FRACASO";
	    }
	}
	return pasa;

    }

}
