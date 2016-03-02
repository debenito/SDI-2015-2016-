package uo.sdi.acciones.gestionUsuarios;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.acciones.Accion;
import uo.sdi.acciones.LogicaFactory;
import uo.sdi.acciones.logic.UsuarioLogica;
import uo.sdi.model.User;
import alb.util.log.Log;

public class ValidarseAction implements Accion {
    // Logica del usuario
    UsuarioLogica logicaUsuario = LogicaFactory.nuevoUsuario();

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	// Parametros de incializacion
	String resultado = "EXITO";
	String nombreUsuario = request.getParameter("nombreUsuario");
	String pass = request.getParameter("pass");
	HttpSession session = request.getSession();

	if (session.getAttribute("user") == null) {
	    User usuario = logicaUsuario.validacionUsuario(nombreUsuario, pass);
	    if (usuario != null) {
		session.setAttribute("user", usuario);
		modificacionContador(request);
		Log.info("El usuario [%s] ha iniciado sesión", nombreUsuario);
	    } else {
		session.invalidate();
		Log.info("El usuario [%s] no está registrado", nombreUsuario);
		resultado = "FRACASO";
	    }
	} else if (!validarUsuario((User) session.getAttribute("user"),
		nombreUsuario)) {
	    Log.info(
		    "Se ha intentado iniciar sesión como [%s] teniendo la sesión iniciada como [%s]",
		    nombreUsuario,
		    ((User) session.getAttribute("user")).getLogin());
	    session.invalidate();
	    resultado = "FRACASO";
	}
	return resultado;
    }

    private void modificacionContador(HttpServletRequest request) {
	int contador = Integer.parseInt((String) request.getServletContext()
		.getAttribute("contador"));
	request.getServletContext().setAttribute("contador",
		String.valueOf(contador + 1));
    }

    private boolean validarUsuario(User userByLogin, String nombreUsuario) {
	return userByLogin != null
		&& nombreUsuario.equals(userByLogin.getLogin());
    }

    @Override
    public String toString() {
	return getClass().getName();
    }

}
