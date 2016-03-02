package uo.sdi.acciones.gestionReservas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.acciones.Accion;
import uo.sdi.acciones.LogicaFactory;
import uo.sdi.acciones.logic.ApplicationLogica;
import alb.util.log.Log;

public class ConfirmarYEliminarUsuarioViajeAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	Long idViaje = Long.parseLong(request.getParameter("idTrip"));
	Long idUsuario = Long.parseLong(request.getParameter("idUser"));
	String confirmar = request.getParameter("confirmar");
	ApplicationLogica logicaApplication = LogicaFactory.nuevoApplication();
	try {
	    String mensaje = null;
	    if(confirmar == null){
		logicaApplication.ponerExcluido(idViaje,idUsuario);
	    }else
	    mensaje=logicaApplication.confirmarPlaza(idViaje,idUsuario);
	  if(mensaje.equals("cupo"))
	      request.setAttribute("cupo","Agotado el cupo de personas");
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
