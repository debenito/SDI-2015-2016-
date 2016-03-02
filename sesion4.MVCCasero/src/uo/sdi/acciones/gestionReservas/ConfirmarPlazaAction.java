package uo.sdi.acciones.gestionReservas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.acciones.Accion;
import uo.sdi.acciones.LogicaFactory;
import uo.sdi.acciones.logic.ApplicationLogica;

public class ConfirmarPlazaAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	Long idViaje = Long.parseLong(request.getParameter("idTrip"));
	Long idUsuario = Long.parseLong(request.getParameter("idUser"));
	ApplicationLogica logicaApplication = LogicaFactory.nuevoApplication();
	try {

	    String mensaje = logicaApplication.confirmarPlaza(idViaje,
		    idUsuario);
	    if (mensaje.equals("cupo"))
		request.setAttribute("cupo", "Agotado el cupo de personas");
	    request.setAttribute(
		    "mensajeConfirmado",
		    "Usted ha confirmado la plaza en el viaje con id "+ idViaje
		    );
	    Log.debug("Usuario confirmado");
	} catch (Exception e) {
	    Log.error("Algo ha ocurrido registrar viaje");
	    e.getMessage();
	}
	return "EXITO";
    }

}
