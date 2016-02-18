package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.UserDao;
import alb.util.log.Log;

public class ModificarDatosAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {

	String passVieja = request.getParameter("pass");
	HttpSession session = request.getSession();
	User usuario = ((User) session.getAttribute("user"));
	User usuarioNuevo = ((User) request.getAttribute("userNuevo"));

	String passNueva = request.getParameter("pass1");
	try {

	    UserDao dao = PersistenceFactory.newUserDao();
	    if (comprobacionContraseña(usuario, passVieja, passNueva)) {
		usuario.setPassword(passNueva);
		dao.update(usuario);
		Log.debug("Modificado usuario [%s] con el valor",
			usuario.getLogin());
	    } else {
		request.setAttribute("mensajeError",
			"Contraseña vieja no coincide o alguna de ellas esta vacia");
		Log.debug(
			"Contraseña vieja de usuario [%s] no coincide con la antigua"
				+ "[%s]", usuario.getLogin(), passVieja);
	    }

	} catch (Exception e) {
	    Log.error("Algo ha ocurrido actualizando el email de [%s]",
		    usuario.getLogin());
	    e.getMessage();
	    String base = "Base de Datos Cerrada";
	    request.getServletContext().setAttribute("baseDatos", base);
	}
	return "EXITO";
    }

    private boolean comprobacionContraseña(User usuario, String passVieja,
	    String passNueva) {

	return passVieja.equals(usuario.getPassword()) && passVieja != ""
		&& passNueva != "";
    }

    @Override
    public String toString() {
	return getClass().getName();
    }

}
