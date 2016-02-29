package com.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Usuario;
import com.sdi.persistence.UsuarioDao;
import com.sdi.persistence.exception.AlreadyPersistedException;
import com.sdi.persistence.exception.PersistenceException;

public class RegistrarseAction extends ControlAbstract {
    private UsuarioDao ud;

    @Override
    public String control(HttpServletRequest request,
	    HttpServletResponse respones) {
	// TODO Auto-generated method stub
	return null;
    }

    @SuppressWarnings("unused")
    @Override
    String executeAccion(HttpServletRequest request,
	    HttpServletResponse response) {
	String login = request.getParameter("login");
	String password2 = request.getParameter("password2");
	String password = request.getParameter("password");
	String apellidos = request.getParameter("apellidos");
	String nombre = request.getParameter("nombre");
	String pasa = "EXITO";
	if (password.equals(password2)) {
	    Usuario u = new Usuario();
	    u.setApellidos(apellidos);
	    u.setEmail(login + "@micorreo.com");
	    u.setPasswd(MD5(password));
	    u.setLogin(login);
	    u.setRol("usuario");
	    u.setNombre(nombre);
	    u.setActivo(false);
	    ud = Factories.persistence.createUsuarioDao();
	    ud.setConection(con);
	    List<Usuario> lista = ud.getUsuarios();
	    if (u != null) {
		for (Usuario usuario : lista) {
		    if (u.getLogin().equals(usuario.getLogin())) {
			pasa = "FRACASO";
			Log.error("Existe un usuario con el mismo login ",
				usuario.getLogin(), u.getLogin());
			request.setAttribute(
				"error",
				"Existe un usuario con el mismo "
					+ "login usuario1 : "
					+ usuario.getLogin() + "  usuario2 : "
					+ u.getLogin());

		    }
		}
		if (pasa != "FRACASO") {
		    try {
			ud = Factories.persistence.createUsuarioDao();
			ud.setConection(con);
			ud.save(u);
			Log.info("El usuario ha sido guardado con exito  ",
				nombre);
			request.setAttribute(
				"exito",
				"El usuario ha sido guardado con exito  "
					+ u.getNombre());
		    } catch (PersistenceException e) {
			// TODO Auto-generated catch block
			pasa = "FRACASO";
			request.setAttribute("error",
				"Problemas al guardar el usuario "
					+ "en la base de datos");

			Log.info(e);
			e.printStackTrace();
		    } catch (AlreadyPersistedException e) {
			// TODO Auto-generated catch block
			request.setAttribute("error",
				"Problemas al guardar el usuario en "
					+ "la base de datos");
			pasa = "FRACASO";
			Log.info(e);

			e.printStackTrace();
		    }
		}
	    } else {

		pasa = "FRACASO";
		Log.info("Usuario nulo", u);
		request.setAttribute("error", "El usuario creado es nulo");
	    }

	} else {
	    pasa = "FRACASO";
	    Log.error("Las contraseña no coinciden ", password, password2);
	    request.setAttribute("error", "Las contraseña no coinciden "
		    + password + "   " + password2);
	}

	return pasa;

    }

    public String MD5(String md5) {
	try {
	    java.security.MessageDigest md = java.security.MessageDigest
		    .getInstance("MD5");
	    byte[] array = md.digest(md5.getBytes());
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < array.length; ++i) {
		sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
			.substring(1, 3));
	    }
	    return sb.toString();
	} catch (java.security.NoSuchAlgorithmException e) {
	}
	return null;
    }

}
