package uo.sdi.acciones.logic;

import java.util.List;

import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;

public interface ViajeLogica {
    List<Trip> listarViajesActivos();

    List<Trip> listarViajesPromotor(Long id);

    void registrar(String departurecity, String departurestate,
	    String departurecountry, String departureaddress,
	    String departurezipCode, String departurewaypointlat,
	    String departurewaypointlon, String arrivalDatefecha,
	    String arrivalDatehora, String destinationcity,
	    String destinationstate, String destinationcountry,
	    String destinationaddress, String destinationzipCode,
	    String destinationwaypointlat, String destinationwaypointlon,
	    String departureDatefecha, String departureDatehora,
	    String estimatedCost, String comments, String maxPax,
	    String availablePax, String closingDatefecha, String closingDatehora
	    ,Long promotorID );

    Trip buscarViajeId(Long l);

    void borrarViaje(Long id);

    void modificarViaje(Long id,String departurecity, String departurestate,
	    String departurecountry, String departureaddress,
	    String departurezipCode, String departurewaypointlat,
	    String departurewaypointlon, String arrivalDatefecha,
	    String arrivalDatehora, String destinationcity,
	    String destinationstate, String destinationcountry,
	    String destinationaddress, String destinationzipCode,
	    String destinationwaypointlat, String destinationwaypointlon,
	    String departureDatefecha, String departureDatehora,
	    String estimatedCost, String comments, String maxPax,
	    String availablePax, String closingDatefecha, String closingDatehora
	    ,Long promotorID ,TripStatus status);
}
