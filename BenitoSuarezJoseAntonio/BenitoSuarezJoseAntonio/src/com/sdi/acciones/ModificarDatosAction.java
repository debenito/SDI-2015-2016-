package com.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Usuario;
import com.sdi.persistence.UsuarioDao;

public class ModificarDatosAction extends ControlAbstract {

    private UsuarioDao ud;

    @Override
    public String toString() {
	return getClass().getName();
    }

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
	    String nombre = request.getParameter("nombre");
	    String apellidos = request.getParameter("apellidos");
	    String passwordAntigua = request.getParameter("password");
	    String password = request.getParameter("password2");
	    String password2 = request.getParameter("password3");

	    String nuevoEmail = request.getParameter("email");
	    HttpSession session = request.getSession();
	    Usuario usuario = ((Usuario) session.getAttribute("user"));

	    if (MD5(passwordAntigua).equals(usuario.getPasswd())
		    && password.equals(password2)) {
		usuario.setEmail(nuevoEmail);
		usuario.setPasswd(MD5(password));
		usuario.setApellidos(apellidos);
		usuario.setNombre(nombre);
		try {
		    ud = Factories.persistence.createUsuarioDao();
		    ud.setConection(con);
		    ud.update(usuario);
		    Log.debug("Modificado usuario de [%s]", usuario.getLogin());
		    request.setAttribute(
			    "exito",
			    "Usuario modificado correctamente  "
				    + usuario.getLogin());
		} catch (Exception e) {
		    Log.error("Algo ha ocurrido actualizando el email de [%s]",
			    usuario.getLogin());
		    request.setAttribute("error",
			    "Algo ha ocurrido actualizando " + "usuario"
				    + " con login " + usuario.getLogin());
		    pasa = "FRACASO";
		}
	    } else if (password != password2) {
		Log.error(
			"La contraseña nueva[%s] no es coincide con la repetida"
				+ " [%s]", password, password2);
		request.setAttribute("error", "La contraseña nueva  "
			+ password + " no coindice con la repetida "
			+ password2);
		pasa = "FRACASO";
	    } else {
		Log.error("La contraseña antigua[%s] no es correcta para"
			+ " el usuario" + " [%s]", passwordAntigua,
			usuario.getLogin());
		request.setAttribute("error",
			"La contraseña antigua no es correcta");
		pasa = "FRACASO";
	    }
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
