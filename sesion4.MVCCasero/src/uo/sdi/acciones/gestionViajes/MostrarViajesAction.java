package uo.sdi.acciones.gestionViajes;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;
import uo.sdi.acciones.Accion;
import uo.sdi.model.Application;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;

public class MostrarViajesAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {

	Trip trip = null;

	try {
	    trip = PersistenceFactory.newTripDao().findById(
		    Long.parseLong(request.getParameter("id")));
	   
	    List<User> viajeros=new ArrayList<User>();
	    List<Application> reservas= PersistenceFactory.newApplicationDao().findByTripId(trip.getId());
	    
	    for(Application a : reservas){
		viajeros.add(PersistenceFactory.newUserDao().findById(a.getUserId()));
		
	    }
	    request.setAttribute("viaje", trip);
	    
		request.setAttribute("viajeros", viajeros);
		
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
