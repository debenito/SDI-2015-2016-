package uo.sdi.bussines.impl;

import java.util.Date;
import java.util.List;

import uo.sdi.bussines.ViajeService;
import uo.sdi.bussines.impl.classes.trip.ViajeBaja;
import uo.sdi.bussines.impl.classes.trip.ViajesAlta;
import uo.sdi.bussines.impl.classes.trip.ViajesListado;
import uo.sdi.bussines.impl.classes.trip.ViajesModificacion;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.types.AddressPoint;
import uo.sdi.model.types.TripStatus;
import uo.sdi.model.types.Waypoint;

public class SimpleViajeService implements ViajeService {

    @Override
    public List<Trip> listarViajesActivos(User usuario) {
	Date hoy = new Date();
	new ViajesModificacion().updateDone(hoy);

	if (usuario == null)
	    return new ViajesListado().listarViajesActivos(hoy);
	else
	    return new ViajesListado().listarViajesActivosPromotor(hoy,
		    usuario.getId());

    }

    @Override
    public List<Trip> listarViajesPromotor(Long id) {

	return new ViajesListado().listaViajesPromotor(id);
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
	    String availablePax, String closingDatefecha,
	    String closingDatehora, Long promotorID) {
	// Creacion de los objetos con los parametros llama a funciones privadas
	Date arrivalDate = modificacionFechasyHoras(arrivalDatefecha,
		arrivalDatehora);
	Date departureDate = modificacionFechasyHoras(departureDatefecha,
		departureDatehora);
	Date closingDate = modificacionFechasyHoras(closingDatefecha,
		closingDatehora);
	Waypoint destinationwaypoint = inicializarWay(destinationwaypointlat,
		destinationwaypointlon);
	Waypoint departurewaypoint = inicializarWay(departurewaypointlat,
		departurewaypointlon);

	AddressPoint departure = new AddressPoint(departureaddress,
		departurecity, departurestate, departurecountry,
		departurezipCode, departurewaypoint);
	AddressPoint destination = new AddressPoint(destinationaddress,
		destinationcity, destinationstate, destinationcountry,
		destinationzipCode, destinationwaypoint);
	Trip viaje = new Trip();
	viaje.setArrivalDate(arrivalDate);
	viaje.setAvailablePax(Integer.parseInt(availablePax));
	viaje.setClosingDate(closingDate);
	viaje.setComments(comments);
	viaje.setDeparture(departure);
	viaje.setDepartureDate(departureDate);
	viaje.setDestination(destination);
	viaje.setEstimatedCost(Double.parseDouble(estimatedCost));
	if (Integer.parseInt(availablePax) < Integer.parseInt(maxPax))
	    viaje.setMaxPax(Integer.parseInt(maxPax));
	else
	    viaje.setMaxPax(Integer.parseInt(availablePax) + 1);
	viaje.setStatus(TripStatus.OPEN);
	viaje.setPromoterId(promotorID);
	new ViajesAlta().save(viaje);

    }

    private Waypoint inicializarWay(String destinationwaypointlat,
	    String destinationwaypointlon) {

	if (vacios(destinationwaypointlat, destinationwaypointlon))
	    return new Waypoint(0.0, 0.0);
	else
	    return new Waypoint(Double.parseDouble(destinationwaypointlat),
		    Double.parseDouble(destinationwaypointlon));
    }

    @Override
    public Trip buscarViajeId(Long l) {

	return new ViajesAlta().buscarViajeId(l);
    }

    @Override
    public void borrarViaje(Long id, Long idUsuario) {
	new ViajeBaja().updateCancel(id);

    }

    @Override
    public void modificarViaje(Long id, String departurecity,
	    String departurestate, String departurecountry,
	    String departureaddress, String departurezipCode,
	    String departurewaypointlat, String departurewaypointlon,
	    String arrivalDatefecha, String arrivalDatehora,
	    String destinationcity, String destinationstate,
	    String destinationcountry, String destinationaddress,
	    String destinationzipCode, String destinationwaypointlat,
	    String destinationwaypointlon, String departureDatefecha,
	    String departureDatehora, String estimatedCost, String comments,
	    String maxPax, String availablePax, String closingDatefecha,
	    String closingDatehora, Long promotorID, TripStatus status) {

	Date arrivalDate = modificacionFechasyHoras(arrivalDatefecha,
		arrivalDatehora);
	Date departureDate = modificacionFechasyHoras(departureDatefecha,
		departureDatehora);
	Date closingDate = modificacionFechasyHoras(closingDatefecha,
		closingDatehora);
	Waypoint destinationwaypoint;
	Waypoint departurewaypoint;
	if (vacios(destinationwaypointlat, destinationwaypointlon))
	    destinationwaypoint = new Waypoint(0.0, 0.0);
	else
	    destinationwaypoint = new Waypoint(
		    Double.parseDouble(departurewaypointlat),
		    Double.parseDouble(departurewaypointlon));
	if (vacios(departurewaypointlat, departurewaypointlon))
	    departurewaypoint = new Waypoint(0.0, 0.0);
	else
	    departurewaypoint = new Waypoint(
		    Double.parseDouble(departurewaypointlat),
		    Double.parseDouble(departurewaypointlon));

	AddressPoint departure = new AddressPoint(departureaddress,
		departurecity, departurestate, departurecountry,
		departurezipCode, departurewaypoint);
	AddressPoint destination = new AddressPoint(destinationaddress,
		destinationcity, destinationstate, destinationcountry,
		destinationzipCode, destinationwaypoint);
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
	if (Integer.parseInt(availablePax) < Integer.parseInt(maxPax))
	    viaje.setMaxPax(Integer.parseInt(maxPax));
	else
	    viaje.setMaxPax(Integer.parseInt(availablePax) + 1);
	viaje.setPromoterId(promotorID);
	viaje.setStatus(status);
	new ViajesModificacion().update(viaje);

    }

    @Override
    public List<Trip> listaViajesParticipacionPromotor(User usuario) {
	return new ViajesListado().listaViajesParticipacionPromotor(usuario
		.getId());
    }

    @Override
    public List<Trip> listaViajesParticipacionPendientes(User usuario) {

	return new ViajesListado().listaViajesParticipacionPendientes(usuario
		.getId());
    }

    @Override
    public List<Trip> listaViajesParticipacionSinPlaza(User usuario) {

	return new ViajesListado().listaViajesParticipacionSinPlaza(usuario
		.getId());
    }

    @Override
    public List<Trip> listaViajesParticipacionAdmitido(User usuario) {

	return new ViajesListado().listaViajesParticipacionAdmitido(usuario
		.getId());
    }

    @Override
    public List<Trip> listaViajesParticipacionExcluido(User usuario) {
	return new ViajesListado().listaViajesParticipacionExcluido(usuario
		.getId());
    }

    private boolean vacios(String destinationwaypointlat,
	    String destinationwaypointlon) {

	return destinationwaypointlat.isEmpty()
		|| destinationwaypointlon.isEmpty();
    }

    private Date modificacionFechasyHoras(String arrivalDatefecha,
	    String arrivalDatehora) {

	String partes[] = arrivalDatefecha.split("-");
	String partes2[] = arrivalDatehora.split(":");
	@SuppressWarnings("deprecation")
	Date d = new Date(Integer.parseInt(partes[0]) - 1900,
		Integer.parseInt(partes[1]) - 1, Integer.parseInt(partes[2]),
		Integer.parseInt(partes2[0]), Integer.parseInt(partes2[1]),
		Integer.parseInt(partes2[2]));
	return d;
    }
}
