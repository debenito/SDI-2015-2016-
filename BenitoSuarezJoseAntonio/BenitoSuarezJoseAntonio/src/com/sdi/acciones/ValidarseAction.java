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

public class ValidarseAction extends ControlAbstract {

    private UsuarioDao ud;
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
	String resultado = "EXITO";
	String nombreUsuario = request.getParameter("nombreUsuario");
	String password = request.getParameter("password");
	List<Contacto> usuarios;

	List<Contacto> privada;
	HttpSession session = request.getSession();
	if (session.getAttribute("user") == null) {
	    ud = Factories.persistence.createUsuarioDao();
	    ud.setConection(con);
	    Usuario usuario = ud.findByLogin(nombreUsuario);
	    if (usuario != null && usuario.getPasswd().equals(MD5(password))
		    && usuario.isActivo()) {
		session.setAttribute("user", usuario);
		int contador = Integer.parseInt((String) request
			.getServletContext().getAttribute("contador"));
		request.getServletContext().setAttribute("contador",
			String.valueOf(contador + 1));

		co = Factories.persistence.createContactoDao();
		co.setConection(con);
		usuarios = co.getAdminContactos();
		if (usuario.getRol() != ("Administrador")) {
		    privada = co.getLoginContactos(usuario.getLogin());
		    session.setAttribute("listaPrivada", privada);
		    Log.debug(
			    "Obtenida lista de contactos conteniendo [%d] usuarios",
			    privada.size());
		}
		session.setAttribute("listaContactos", usuarios);

		Log.debug(
			"Obtenida lista de contactos conteniendo [%d] usuarios",
			usuarios.size());

		Log.info("El usuario [%s] ha iniciado sesión", nombreUsuario);

	    } else if (!usuario.isActivo()) {
		Log.info("El usuario todabia no esta activo espere a que "
			+ "sea activado por el administrador", usuario.getId());
		request.setAttribute("error",
			"El usuario con id " + usuario.getId()
				+ " no esta activo espere a que sea"
				+ " activado por el administrador");
		resultado = "FRACASO";
	    } else if (usuario.getPasswd() != MD5(password)) {
		Log.info("Contraseña incorrecta", password);
		request.setAttribute("error", "Contraseña inconrrecta");
		resultado = "FRACASO";
	    } else {

		Log.info("El usuario [%s] no está registrado", nombreUsuario);
		request.setAttribute("error", "El usuario " + nombreUsuario
			+ " no está registrado" + nombreUsuario);
		resultado = "FRACASO";
		session.invalidate();
	    }
	} else if (!nombreUsuario.equals(session.getAttribute("user"))) {

	    Log.info("Se ha intentado iniciar sesión como [%s] teniendo "
		    + "la sesión iniciada como [%s]", nombreUsuario,
		    ((Usuario) session.getAttribute("user")).getNombre());
	    request.setAttribute(
		    "error",
		    "Se ha intentado iniciar sesión como "
			    + nombreUsuario
			    + " teniendo la sesión iniciada como "
			    + nombreUsuario
			    + "  "
			    + ((Usuario) session.getAttribute("user"))
				    .getNombre());
	    resultado = "FRACASO";
	    session.invalidate();
	}

	return resultado;

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
