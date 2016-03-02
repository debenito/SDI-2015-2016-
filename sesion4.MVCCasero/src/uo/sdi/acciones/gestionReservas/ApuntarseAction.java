package uo.sdi.acciones.gestionReservas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.acciones.Accion;
import uo.sdi.acciones.LogicaFactory;
import uo.sdi.acciones.logic.ApplicationLogica;
import uo.sdi.model.User;
import alb.util.log.Log;

public class ApuntarseAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	HttpSession session = request.getSession();
	User usuario = (User) session.getAttribute("user");
	Long id = Long.parseLong(request.getParameter("id"));
	ApplicationLogica logicaApplication = LogicaFactory.nuevoApplication();
	try {
	    logicaApplication.apuntarse(id, usuario.getId());
	    request.setAttribute(
		    "mensajeApuntado",
		    "Usted esta apuntado al viaje con id "+ id +" ,este se mostrara en mis viajes"
		    );
	    Log.debug("Usuario apuntado correctamete");
	} catch (Exception e) {
	    Log.error("Algo ha ocurrido registrar viaje [%s] del usuario [%s]",id, usuario.getId());
	    e.getMessage();
	    String base = "Base de Datos Cerrada";
	    request.getServletContext().setAttribute("baseDatos", base);

	}
	return "EXITO";

    }

}
