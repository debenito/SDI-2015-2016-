package uo.sdi.acciones;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.resteasy.util.DateUtil;

import alb.util.log.Log;
import uo.sdi.model.AddressPoint;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.Waypoint;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;


public class AddViajeAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {

	Trip viaje = registrarViaje(request);
	try {
		if(viaje !=null){
		TripDao dao = PersistenceFactory.newTripDao();
		//dao.save(viaje);
		Log.debug("Registrado el viaje [%s] con exito",
				viaje.getId());
		request.setAttribute("mensajeError", "Exito al introducir el viaje");
		request.getServletContext().setAttribute("baseDatos","Abierta");
		}
	} catch (Exception e) {
		Log.error("Algo ha ocurrido registrado al viaje [%s]",
				viaje.getId());
		e.getMessage();
		String base ="Base de Datos Cerrada";
		request.getServletContext().setAttribute("baseDatos",base);
		
	}
	return "EXITO";

    }

    private Trip registrarViaje(HttpServletRequest request) {
	// TODO Auto-generated method stub
	Trip trip= new Trip();
	User promotor= (User) request.getSession().getAttribute("user");
	String calle = request.getParameter("calle");
	String ciudad = request.getParameter("ciudad");
	String pais = request.getParameter("pais");
	String provincia = request.getParameter("provincia");
	String cp = request.getParameter("cp");
	Waypoint wpSalida = new Waypoint(1.0,1.0);
	
	

	AddressPoint salida = new AddressPoint(calle, ciudad, provincia, pais,
		cp, wpSalida);

	//Date fechaSalida = DateUtil.parseDate(request
	//	.getParameter("fechaSalida"));
	//long horaSalida = Long.parseLong(request.getParameter("horaSalida"));

	trip.setDeparture(salida);
	//trip.setDepartureDate(fechaSalida);
	String calleDestino=request.getParameter("calleDestino");
	String ciudadDestino=request.getParameter("ciudadDestino");
	String paisDestino=request.getParameter("paisDestino");
	String provinciaDestino=request.getParameter("provinciaDestino");
	String cpDestino=request.getParameter("cpDestino");
	Waypoint wpDestino=new Waypoint(1.0, 1.0);
	
	
	
	AddressPoint llegada= new AddressPoint(calleDestino, ciudadDestino, provinciaDestino, paisDestino, cpDestino, wpDestino);
	
	//Date fechaLlegada= DateUtil.parseDate(request.getParameter("fechaLlegada"));
	//long horaLlegada= Long.parseLong(request.getParameter("horaLlegada")); 
	
	trip.setDestination(llegada);
	//trip.setArrivalDate(fechaLlegada);
	//Date limite=DateUtil.parseDate(request.getParameter("fechaLimite"));
		//double coste= Double.parseDouble(request.getParameter("coste"));
		int libre=Integer.parseInt(request.getParameter("plazas"));
		int maximo= Integer.parseInt(request.getParameter("maximo"));
		
		String observaciones= request.getParameter("observaciones");
		
		//trip.setClosingDate(limite);
		trip.setMaxPax(maximo);
		trip.setAvailablePax(libre);
		//trip.setEstimatedCost(coste);
		trip.setComments(observaciones);
	trip.setPromoterId(promotor.getId());

	return trip;
    }

    private void setDeparture(Trip trip, HttpServletRequest request) {
	String calle = request.getParameter("calle");
	String ciudad = request.getParameter("ciudad");
	String pais = request.getParameter("pais");
	String provincia = request.getParameter("provincia");
	String cp = request.getParameter("cp");
	Waypoint wpSalida = new Waypoint(1.0, 1.0);
	
	if (request.getParameter("alt") != null && request.getParameter("lat") != null) {
	    double alt = Double.parseDouble(request.getParameter("alt"));
	    double lat = Double.parseDouble(request.getParameter("lat"));
	    wpSalida = new Waypoint(alt, lat);
	}

	AddressPoint salida = new AddressPoint(calle, ciudad, provincia, pais,
		cp, wpSalida);

	//Date fechaSalida = DateUtil.parseDate(request
	//	.getParameter("fechaSalida"));
	long horaSalida = Long.parseLong(request.getParameter("horaSalida"));

	trip.setDeparture(salida);
	//trip.setDepartureDate(fechaSalida);
    }

    private void setArrival(Trip trip, HttpServletRequest request) {
	String calleDestino=request.getParameter("calleDestino");
	String ciudadDestino=request.getParameter("ciudadDestino");
	String paisDestino=request.getParameter("paisDestino");
	String provinciaDestino=request.getParameter("provinciaDestino");
	String cpDestino=request.getParameter("cpDestino");
	Waypoint wpDestino=new Waypoint(1.0, 1.0);
	
	if(request.getParameter("altDestino")!=null && request.getParameter("latDestino")!=null){
	    double altDestino=Double.parseDouble(request.getParameter("altDestino"));
	    double latDestino=Double.parseDouble(request.getParameter("latDestino"));
	    wpDestino=new Waypoint(altDestino, latDestino);
	}
	
	AddressPoint llegada= new AddressPoint(calleDestino, ciudadDestino, provinciaDestino, paisDestino, cpDestino, wpDestino);
	
	//Date fechaLlegada= DateUtil.parseDate(request.getParameter("fechaLlegada"));
	long horaLlegada= Long.parseLong(request.getParameter("horaLlegada")); 
	
	trip.setDestination(llegada);
	//trip.setArrivalDate(fechaLlegada);
    }

    private void setExtras(Trip trip, HttpServletRequest request) {
	//Date limite=DateUtil.parseDate(request.getParameter("fechaLimite"));
	double coste= Double.parseDouble(request.getParameter("coste"));
	//int libre=Integer.parseInt();
	int maximo= Integer.parseInt("maximo");
	
	String observaciones= request.getParameter("observaciones");
	
	//trip.setClosingDate(limite);
	trip.setMaxPax(maximo);
	//trip.setAvailablePax(libre);
	trip.setEstimatedCost(coste);
	trip.setComments(observaciones);
    }

}