package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import alb.util.log.Log;

public class VerMisViajes implements Accion{

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	List<Trip> viajes;
	String base ="Base de Datos Cerrada";
	try {
	    HttpSession session= request.getSession();
	    User user=(User) session.getAttribute("user");
		viajes=PersistenceFactory.newTripDao().findByPromoterId(user.getId());
		request.setAttribute("listaViajes", viajes);
		Log.debug("Obtenida lista de viajes conteniendo [%d] viajes", viajes.size());
	}
	catch (Exception e) {
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
