package uo.sdi.acciones.gestionUsuarios;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.acciones.Accion;
import uo.sdi.acciones.LogicaFactory;
import uo.sdi.acciones.logic.UsuarioLogica;
import uo.sdi.model.User;
import alb.util.log.Log;

public class RegistrarDatosAction implements Accion {
    // Logica del usuario
    UsuarioLogica logicaUsuario = LogicaFactory.nuevoUsuario();

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	// Parametros de inicializacion
	String login = request.getParameter("login");
	String nombre = request.getParameter("nombre");
	String apellidos = request.getParameter("apellidos");
	String nuevoEmail = request.getParameter("email");
	String passNueva1 = request.getParameter("pass1");
	String passNueva2 = request.getParameter("pass2");

	

	try {
	   if(comprobacionPassIguales(passNueva1, passNueva2)){
	logicaUsuario.registrarUsuario(new User(), login, nombre, apellidos, passNueva1, nuevoEmail);
		Log.debug("Registrado el usuario [%s] con exito",
			nombre);
		request.setAttribute("mensajeError",
			"Exito al introducir el usuario con login "+ login);
		request.getServletContext()
			.setAttribute("baseDatos", "Abierta");
	   }else
	       request.setAttribute("mensajeError",
			    "Contraseñas diferentes Contraseña1 :" + passNueva1
				    + " Contraseña2 :" + passNueva2);
	} catch (Exception e) {
	    Log.error("Algo ha ocurrido registrado al usuario [%s]",
		    login);
	    e.getMessage();
	    String base = "Base de Datos Cerrada";
	    request.getServletContext().setAttribute("baseDatos", base);

	}
	return "EXITO";
    }

   

    private boolean comprobacionPassIguales(String passNueva1, String passNueva2) {
	if (passNueva1.equals(passNueva2)) {
	    Log.info("Las contraseñas son iguales");
	    return true;
	} else {
	    Log.error(
		    "Contraseñas diferentes Contraseña1 : %s Contraseña2 : %s",
		    passNueva1, passNueva2);
	    return false;
	}
    }

    @Override
    public String toString() {
	return getClass().getName();
    }

}
