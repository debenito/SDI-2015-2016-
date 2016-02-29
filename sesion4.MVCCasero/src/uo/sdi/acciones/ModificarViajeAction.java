package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.AddressPoint;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.Waypoint;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;
import alb.util.log.Log;

public class ModificarViajeAction implements Accion{

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	Trip trip = null;
	trip = PersistenceFactory.newTripDao().findById(
		    Long.parseLong(request.getParameter("id")));
	    request.setAttribute("viaje", trip);
	Trip viaje = modificarViaje(request);
	try {
		if(viaje !=null){
		TripDao dao = PersistenceFactory.newTripDao();
		//dao.save(viaje);
		Log.debug("Modificado el viaje [%s] con exito",
				viaje.getId());
		request.setAttribute("mensajeError", "Exito al modificar el viaje");
		request.getServletContext().setAttribute("baseDatos","Abierta");
		}
	} catch (Exception e) {
		Log.error("Algo ha ocurrido modificando al viaje [%s]",
				viaje.getId());
		e.getMessage();
		String base ="Base de Datos Cerrada";
		request.getServletContext().setAttribute("baseDatos",base);
		
	}
	return "EXITO";

    }
    
    private Trip modificarViaje(HttpServletRequest request) {
	Trip trip= new Trip();
	setDeparture(trip, request);
	setArrival(trip, request);
	setExtras(trip, request);
	return trip;
    }
    private void setDeparture(Trip trip, HttpServletRequest request) {
   	String calle = request.getParameter("calle");
   	String ciudad = request.getParameter("ciudad");
   	String pais = request.getParameter("pais");
   	String provincia = request.getParameter("provincia");
   	String cp = request.getParameter("cp");
   	Waypoint wpSalida = new Waypoint(1.0, 1.0);
   	
   	if (request.getParameter("lat") != null && request.getParameter("lon") != null) {
   	  //  double lat = Double.parseDouble(request.getParameter("lat"));
   	    //double lon = Double.parseDouble(request.getParameter("lon"));
   	    //wpSalida = new Waypoint(lat, lon);
   	}

   	AddressPoint salida = new AddressPoint(calle, ciudad, provincia, pais,
   		cp, wpSalida);

   	//Date fechaSalida = DateUtil.parseDate(request
   	//	.getParameter("fechaSalida"));
   	//long horaSalida = Long.parseLong(request.getParameter("horaSalida"));

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
   	
   	if(request.getParameter("latDestino")!=null && request.getParameter("lonDestino")!=null){
   	//    double latDestino=Double.parseDouble(request.getParameter("alatDestino"));
   	  //  double lonDestino=Double.parseDouble(request.getParameter("lonDestino"));
   	    //wpDestino=new Waypoint(latDestino, lonDestino);
   	}
   	
   	AddressPoint llegada= new AddressPoint(calleDestino, ciudadDestino, provinciaDestino, paisDestino, cpDestino, wpDestino);
   	
   	//Date fechaLlegada= DateUtil.parseDate(request.getParameter("fechaLlegada"));
   	//long horaLlegada= Long.parseLong(request.getParameter("horaLlegada")); 
   	
   	trip.setDestination(llegada);
   	//trip.setArrivalDate(fechaLlegada);
       }

       private void setExtras(Trip trip, HttpServletRequest request) {
   	//Date limite=DateUtil.parseDate(request.getParameter("fechaLimite"));
   	//double coste= Double.parseDouble(request.getParameter("coste"));
   	//int libre=Integer.parseInt();
   	//int maximo= Integer.parseInt("maximo");
   	
   	String observaciones= request.getParameter("observaciones");
   	
   	//trip.setClosingDate(limite);
   	//trip.setMaxPax(maximo);
   	//trip.setAvailablePax(libre);
   	//trip.setEstimatedCost(coste);
   	trip.setComments(observaciones);
       }


}
