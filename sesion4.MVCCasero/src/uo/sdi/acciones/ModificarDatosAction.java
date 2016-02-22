package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.acciones.logic.UsuarioLogica;
import uo.sdi.model.User;
import alb.util.log.Log;

public class ModificarDatosAction implements Accion {
    // Logica del usuario
    UsuarioLogica logicaUsuario = LogicaFactory.nuevoUsuario();

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	// parametros rescatados de la sesion
	HttpSession session = request.getSession();
	User usuario = ((User) session.getAttribute("user"));
	String passNueva = request.getParameter("pass");
	String passVieja = request.getParameter("passVieja");
	String passNueva1 = request.getParameter("pass1");
	String nombre = request.getParameter("name");
	String apellidos = request.getParameter("surname");
	String email = request.getParameter("email");
	String login = request.getParameter("login");
	try {
	    if (!comprobacionContraseñasIguales(passNueva, passNueva1)) {
		request.setAttribute("mensajeError",
			"Contraseñas diferentes contraseña 1 :" + passNueva
				+ " Contraseña 2 :" + passNueva1);
		Log.error(
			"Contraseñas diferentes contraseña 1 :[%s] Contraseña 2 : [%s]",
			passNueva, passNueva1);
	    } else if (comprobacionContraseñaVieja(passVieja, usuario)) {
		logicaUsuario.modificar(usuario, email, login, nombre,
			passNueva1, apellidos);
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
	    Log.error("Algo ha ocurrido actualizando usuario de login [%s]",
		    usuario.getLogin());
	    e.getMessage();
	    String base = "Base de Datos Cerrada";
	    request.getServletContext().setAttribute("baseDatos", base);
	}
	return "EXITO";
    }

    private boolean comprobacionContraseñasIguales(String passNueva,
	    String passNueva1) {

	return passNueva.equals(passNueva1);
    }

    private boolean comprobacionContraseñaVieja(String passVieja, User usuario) {
	String contraseñaVieja = logicaUsuario
		.comprobacionContraseñaVieja(usuario);
	return passVieja.equals(contraseñaVieja);
    }

    @Override
    public String toString() {
	return getClass().getName();
    }

}
