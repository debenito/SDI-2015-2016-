package uo.sdi.bussines;

import java.util.List;

import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.types.TripStatus;

public interface ViajeService {
    List<Trip> listarViajesActivos(User usuario);

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
	    String availablePax, String closingDatefecha,
	    String closingDatehora, Long promotorID);

    Trip buscarViajeId(Long l);

    void borrarViaje(Long id, Long idUsuario);

    void modificarViaje(Long id, String departurecity, String departurestate,
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
	    String closingDatehora, Long promotorID, TripStatus status);

    List<Trip> listaViajesParticipacionPromotor(User usuario);

    List<Trip> listaViajesParticipacionPendientes(User usuario);

    List<Trip> listaViajesParticipacionSinPlaza(User usuario);

    List<Trip> listaViajesParticipacionAdmitido(User usuario);

    List<Trip> listaViajesParticipacionExcluido(User usuario);

}
