package uo.sdi.acciones.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import uo.sdi.acciones.logic.ViajeLogica;
import uo.sdi.model.AddressPoint;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.Waypoint;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;

public class ViajeLogic implements ViajeLogica {
    private TripDao dao = PersistenceFactory.newTripDao();
    
    
    @Override
    public List<Trip> listarViajesActivos() {
	@SuppressWarnings("deprecation")
	    Date hoy = new Date(Calendar.YEAR, Calendar.MONTH, Calendar.DATE,
		    Calendar.HOUR, Calendar.MINUTE, Calendar.SECOND);
	dao.updateDone(hoy);
	return dao.listarViajesActivos(hoy);
    }

   
    @Override
    public List<Trip> listarViajesPromotor(Long id) {
	
	return dao.listarViajesPromotor(id);
    }


    @Override
    public void registrar(String departurecity, String departurestate,
	    String departurecountry, String departureaddress,
	    String departurezipCode, String departurewaypointlat,
	    String departurewaypointlon, String arrivalDatefecha,
	    String arrivalDatehora, String destinationcity,
	    String destinationstate, String destinationcountry,
	    String destinationaddress, String destinationzipCode,
	    String destinationwaypointlat, String destinationwaypointlon,
	    String departureDatefecha, String departureDatehora,
	    String estimatedCost, String comments, String maxPax,
	    String availablePax, String closingDatefecha, String closingDatehora,Long promotorID) {
	Date arrivalDate = modificacionFechasyHoras(arrivalDatefecha,arrivalDatehora);
	Date departureDate = modificacionFechasyHoras(departureDatefecha,departureDatehora);
	Date closingDate = modificacionFechasyHoras(closingDatefecha,closingDatehora);
	Waypoint destinationwaypoint = inicializarWay(destinationwaypointlat,destinationwaypointlon);
	Waypoint departurewaypoint = inicializarWay(departurewaypointlat,departurewaypointlon);
	
	
	AddressPoint departure = new AddressPoint(departureaddress,departurecity,departurestate,departurecountry,
		departurezipCode,departurewaypoint);
	AddressPoint destination = new AddressPoint(destinationaddress, destinationcity, destinationstate,
		destinationcountry, destinationzipCode, destinationwaypoint);
	Trip viaje = new Trip();
	viaje.setArrivalDate(arrivalDate);
	viaje.setAvailablePax(Integer.parseInt(availablePax));
	viaje.setClosingDate(closingDate);
	viaje.setComments(comments);
	viaje.setDeparture(departure);
	viaje.setDepartureDate(departureDate);
	viaje.setDestination(destination);
	viaje.setEstimatedCost(Double.parseDouble(estimatedCost));
	viaje.setMaxPax(Integer.parseInt(maxPax));
	viaje.setStatus(TripStatus.OPEN);
	viaje.setPromoterId(promotorID);
	dao.save(viaje);
	
    }


    private Waypoint inicializarWay(String destinationwaypointlat,
	    String destinationwaypointlon) {
	
	if(vacios(destinationwaypointlat,destinationwaypointlon))
	    return new Waypoint(0.0,0.0); 
	else
	    return new Waypoint(Double.parseDouble(destinationwaypointlat),
		Double.parseDouble(destinationwaypointlon));
    }


    @Override
    public Trip buscarViajeId(Long l) {

	return dao.findById(l);
    }


    @Override
    public void borrarViaje(Long id) {
	dao.updateCancel(id);
	
    }


    @Override
    public void modificarViaje(Long id,String departurecity, String departurestate,
	    String departurecountry, String departureaddress,
	    String departurezipCode, String departurewaypointlat,
	    String departurewaypointlon, String arrivalDatefecha,
	    String arrivalDatehora, String destinationcity,
	    String destinationstate, String destinationcountry,
	    String destinationaddress, String destinationzipCode,
	    String destinationwaypointlat, String destinationwaypointlon,
	    String departureDatefecha, String departureDatehora,
	    String estimatedCost, String comments, String maxPax,
	    String availablePax, String closingDatefecha, String closingDatehora,Long promotorID,TripStatus status) {
	
	Date arrivalDate = modificacionFechasyHoras(arrivalDatefecha,arrivalDatehora);
	Date departureDate = modificacionFechasyHoras(departureDatefecha,departureDatehora);
	Date closingDate = modificacionFechasyHoras(closingDatefecha,closingDatehora);
	Waypoint destinationwaypoint;
	Waypoint departurewaypoint;
	if(vacios(destinationwaypointlat,destinationwaypointlon))
	    destinationwaypoint = new Waypoint(0.0,0.0);
	else
	 destinationwaypoint = new Waypoint(Double.parseDouble(departurewaypointlat),
		Double.parseDouble(departurewaypointlon));
	if(vacios(departurewaypointlat,departurewaypointlon))
	    departurewaypoint = new Waypoint(0.0,0.0);
	else
	    departurewaypoint = new Waypoint(Double.parseDouble(departurewaypointlat),
		Double.parseDouble(departurewaypointlon));
	
	AddressPoint departure = new AddressPoint(departureaddress,departurecity,departurestate,departurecountry,
		departurezipCode,departurewaypoint);
	AddressPoint destination = new AddressPoint(destinationaddress, destinationcity, destinationstate,
		destinationcountry, destinationzipCode, destinationwaypoint);
	Trip viaje = new Trip();
	viaje.setId(id);
	viaje.setArrivalDate(arrivalDate);
	viaje.setAvailablePax(Integer.parseInt(availablePax));
	viaje.setClosingDate(closingDate);
	viaje.setComments(comments);
	viaje.setDeparture(departure);
	viaje.setDepartureDate(departureDate);
	viaje.setDestination(destination);
	viaje.setEstimatedCost(Double.parseDouble(estimatedCost));
	viaje.setMaxPax(Integer.parseInt(maxPax));
	viaje.setPromoterId(promotorID);
	viaje.setStatus(status);
	dao.update(viaje);
	
    }


    private boolean vacios(String destinationwaypointlat,
	    String destinationwaypointlon) {
	
	return destinationwaypointlat.isEmpty() || destinationwaypointlon.isEmpty();
    }


    private Date modificacionFechasyHoras(String arrivalDatefecha,
	    String arrivalDatehora) {
	
	String partes[] = arrivalDatefecha.split("-");
	String partes2[]= arrivalDatehora.split(":");
	@SuppressWarnings("deprecation")
	Date d = new Date(Integer.parseInt(partes[0])-1900,
		Integer.parseInt(partes[1])-1,
		Integer.parseInt(partes[2]),
		Integer.parseInt(partes2[0]),
		Integer.parseInt(partes2[1]),
		Integer.parseInt(partes2[2]));
	return d;
    }

}
