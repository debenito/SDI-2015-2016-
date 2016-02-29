package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.acciones.logic.UsuarioLogica;
import uo.sdi.acciones.logic.ViajeLogica;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import alb.util.log.Log;

public class ModificarViajeAction implements Accion {
    // Logica del usuario
    UsuarioLogica logicaUsuario = LogicaFactory.nuevoUsuario();

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	// Parametros de inicializacion
	String departurecity = request.getParameter("departurecity");
	String departurestate = request.getParameter("departurestate");
	String departurecountry = request.getParameter("departurecountry");
	String departureaddress = request.getParameter("departureaddress");
	String departurezipCode = request.getParameter("departurezipCode");
	String departurewaypointlat = request.getParameter("departurewaypointlat");
	String departurewaypointlon = request.getParameter("departurewaypointlon");
	String arrivalDatefecha = request.getParameter("arrivalDatefecha");
	String arrivalDatehora = request.getParameter("arrivalDatehora");
	String destinationcity = request.getParameter("destinationcity");
	String destinationstate = request.getParameter("destinationstate");
	String destinationcountry = request.getParameter("destinationcountry");
	String destinationaddress = request.getParameter("destinationaddress");
	String destinationzipCode = request.getParameter("destinationzipCode");
	String destinationwaypointlat = request.getParameter("destinationwaypointlat");
	String destinationwaypointlon = request.getParameter("destinationwaypointlon");
	String departureDatefecha = request.getParameter("departureDatefecha");
	String departureDatehora = request.getParameter("departureDatehora");
	String estimatedCost = request.getParameter("estimatedCost");
	String comments = request.getParameter("comment");
	String maxPax = request.getParameter("maxPax");
	String availablePax = request.getParameter("availablePax");
	String closingDatefecha = request.getParameter("departureDatefecha");
	String closingDatehora = request.getParameter("departureDatehora");
	Long id = Long.parseLong(request.getParameter("id"));
	TripStatus status = TripStatus.valueOf(request.getParameter("status"));
	 ViajeLogica logicaViajes = LogicaFactory.nuevoViaje();
	 HttpSession session = request.getSession();
	 User user = (User) session.getAttribute("user");
	try {
	   logicaViajes.modificarViaje(id,departurecity,departurestate,departurecountry,departureaddress,
		   departurezipCode,departurewaypointlat,departurewaypointlon,arrivalDatefecha,
		   arrivalDatehora,destinationcity,destinationstate,destinationcountry,
		   destinationaddress,destinationzipCode,destinationwaypointlat,destinationwaypointlon,
		   departureDatefecha,departureDatehora,estimatedCost,comments,maxPax,availablePax,
		   closingDatefecha,closingDatehora,user.getId(),status);
	   List<Trip>viajesPromotor = logicaViajes.listarViajesPromotor(user.getId());
	    request.setAttribute("viajesPromotor", viajesPromotor);
	    Log.debug("Obtenida lista de viajes del promotor conteniendo [%d] viajes",
		    viajesPromotor.size());
	} catch (Exception e) {
	    Log.error("Algo ha ocurrido modificar viaje");
	    e.getMessage();
	    String base = "Base de Datos Cerrada";
	    request.getServletContext().setAttribute("baseDatos", base);

	}
	return "EXITO";
    }

    

    @Override
    public String toString() {
	return getClass().getName();
    }

}
