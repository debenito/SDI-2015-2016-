package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.acciones.logic.ViajeLogica;
import uo.sdi.model.Rating;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import alb.util.log.Log;

public class ViajesPromotorAction implements Accion {
    // Logica Viajes
    ViajeLogica logicaViajes = LogicaFactory.nuevoViaje();

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {

	List<Trip> viajesPromotor;
	HttpSession session = request.getSession();
	User usuario = (User) session.getAttribute("user");
	String base = "Base de Datos Cerrada";
	try {
	    viajesPromotor = logicaViajes.listarViajesPromotor(usuario.getId());
	    request.setAttribute("viajesPromotor", viajesPromotor);
	    Log.debug("Obtenida lista de viajes del promotor conteniendo [%d] viajes",
		    viajesPromotor.size());
	  
	    request.getServletContext().setAttribute("baseDatos", "Abierta");
	} catch (Exception e) {
	    Log.error("Algo ha ocurrido obteniendo lista de viajes");
	    e.getMessage();

	    request.getServletContext().setAttribute("baseDatos", base);
	}
	return "EXITO";
    }

    @Override
    public String toString() {
	return getClass().getName();
    }

}
