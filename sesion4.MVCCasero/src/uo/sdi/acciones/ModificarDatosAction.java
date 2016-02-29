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
	   
	    if (comprobacionContraseñaVieja(passVieja, usuario)
		    && comprobacionContraseñasIguales(passNueva, passNueva1) &&
		    contraseñasNuevasNoVacias(passNueva,passNueva1)) {
		usuario =logicaUsuario.modificar(usuario, email, login, nombre,
			passNueva1, apellidos);
		session.setAttribute("user", usuario);
		Log.debug("Modificado usuario [%s] con el valor",
			login);
	    } else if(comprobacionContraseñaVieja(passVieja, usuario)
		    &&  !contraseñasNuevasNoVacias(passNueva,passNueva1)) {
		usuario =logicaUsuario.modificar(usuario, email, login, nombre,
			passVieja, apellidos);
		session.setAttribute("user", usuario);
		Log.debug("Modificado usuario [%s] con el valor",
			login);
	    }else{
		if(!comprobacionContraseñasIguales(passNueva, passNueva1)){
		    request.setAttribute("mensajeError",
				"Contraseña nuevas no coinciden");
			Log.debug(
				"Contraseña nueva de usuario [%s] no coincide con la repetida"
					+ "[%s]", passNueva, passNueva1);
		}else{
		request.setAttribute("mensajeError",
			"Contraseña vieja no coincide o alguna de ellas esta vacia");
		Log.debug(
			"Contraseña vieja de usuario [%s] no coincide con la antigua"
				+ "[%s]", usuario.getLogin(), passVieja);
	    }
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

    private boolean contraseñasNuevasNoVacias(String passNueva,
	    String passNueva1) {
	return !passNueva.isEmpty() && !passNueva1.isEmpty();
    }

    private boolean comprobacionContraseñasIguales(String passNueva,
	    String passNueva1) {

	return passNueva.equals(passNueva1)   ;
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
