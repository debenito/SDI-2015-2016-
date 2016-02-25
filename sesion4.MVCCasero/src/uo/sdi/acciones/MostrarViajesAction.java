package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Trip;
import uo.sdi.persistence.PersistenceFactory;

public class MostrarViajesAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {

	Trip trip = null;

	try {
	    trip = PersistenceFactory.newTripDao().findById(
		    Long.parseLong(request.getParameter("id")));
	    request.setAttribute("viaje", trip);
	    Log.debug("Obtenida informacion del viaje");
	} catch (Exception e) {
	    Log.error("Algo ha ocurrido obteniendo lista de viajes");
	    e.getMessage();
	}
	return "EXITO";
    }

    @Override
    public String toString() {
	return getClass().getName();
    }

}
