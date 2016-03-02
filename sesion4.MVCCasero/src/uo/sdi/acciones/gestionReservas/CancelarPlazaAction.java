package uo.sdi.acciones.gestionReservas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.acciones.Accion;
import uo.sdi.acciones.LogicaFactory;
import uo.sdi.acciones.logic.ApplicationLogica;
import uo.sdi.model.User;
import alb.util.log.Log;

public class CancelarPlazaAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	HttpSession session = request.getSession();
	User usuario = (User) session.getAttribute("user");
	Long idViaje = Long.parseLong(request.getParameter("id"));
	Long idUsuario = usuario.getId();
	ApplicationLogica logicaApplication = LogicaFactory.nuevoApplication();
	try {
	    logicaApplication.cancelarPlaza(idViaje,idUsuario);
	  
	    Log.debug("Usuario eliminado correctamete");
	} catch (Exception e) {
	    Log.error("Algo ha ocurrido registrar viaje");
	    e.getMessage();
	    String base = "Base de Datos Cerrada";
	    request.getServletContext().setAttribute("baseDatos", base);

	}
	return "EXITO";

    }

}
