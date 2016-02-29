package com.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Usuario;
import com.sdi.persistence.UsuarioDao;
import com.sdi.persistence.exception.NotPersistedException;
import com.sdi.persistence.exception.PersistenceException;

public class ModificarDatosUsuariosAction extends ControlAbstract {
    private UsuarioDao ud;

    @Override
    public String control(HttpServletRequest request,
	    HttpServletResponse respones) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    String executeAccion(HttpServletRequest request,
	    HttpServletResponse response) {
	String login = request.getParameter("login");
	String password2 = request.getParameter("password2");
	String password = request.getParameter("password");
	String apellidos = request.getParameter("apellidos");
	String nombre = request.getParameter("nombre");
	String email = request.getParameter("email");
	String rol = request.getParameter("rol");
	int id = Integer.parseInt(request.getParameter("id"));
	String activo = request.getParameter("activo");
	boolean activado = false;
	if (activo != null) {
	    activado = true;
	}
	Usuario u = new Usuario();
	u.setActivo(activado);
	u.setApellidos(apellidos);
	u.setEmail(email);
	u.setLogin(login);
	u.setNombre(nombre);
	u.setPasswd(MD5(password));
	u.setRol(rol);
	u.setId(id);
	String pasa = "EXITO";
	if (password.equals(password2)) {
	    if (u != null) {
		try {
		    ud = Factories.persistence.createUsuarioDao();
		    ud.setConection(con);
		    ud.update(u);
		    Log.info("Usuario actualizado correctamente", u.getNombre());
		    request.setAttribute(
			    "exito",
			    "Se ha actualizado el usuario "
				    + " correctamete con login  "
				    + u.getLogin());

		} catch (PersistenceException e) {
		    // TODO Auto-generated catch block
		    Log.info("Problemas al actualizar usuario", u.getNombre());
		    request.setAttribute("error",
			    "Problemas al actualizar el usuario");
		    pasa = "FRACASO";
		    e.printStackTrace();
		} catch (NotPersistedException e) {
		    // TODO Auto-generated catch block
		    Log.info("Problemas al actualizar usuario", u.getNombre());
		    request.setAttribute("error",
			    "Problemas al actualizar el usuario");
		    pasa = "FRACASO";
		    e.printStackTrace();
		}
	    }

	} else {
	    Log.info("Las contraseñas no coinciden", password, password2);
	    request.setAttribute("error", "Las contraseñas no coinciden");
	    pasa = "FRACASO";
	}
	return pasa;

    }

    private String MD5(String md5) {
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
