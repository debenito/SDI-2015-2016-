package uo.sdi.bussines.impl.classes.application;

import uo.sdi.bussines.Command;
import uo.sdi.infraestructure.Factories;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.types.SeatStatus;
import uo.sdi.persistence.util.Jpa;

public class ConfirmarPlaza implements Command {

    private Long idViaje;
    private Long idUsuario;

    public ConfirmarPlaza(Long idViaje, Long idUsuario) {
	this.idViaje = idViaje;
	this.idUsuario = idUsuario;
    }

    @Override
    public Object execute() {
	Trip viaje = Factories.persistence.newTripDao().findById(idViaje);
	if (!exitePasajero(idViaje, idUsuario) && viaje.getAvailablePax() != 0) {
	    viaje.setAvailablePax(viaje.getAvailablePax() - 1);
	    Jpa.getManager().merge(viaje);

	    Seat asiento = crearAsiento(idUsuario, idViaje);
	    asiento.setStatus(SeatStatus.ACCEPTED);
	    Jpa.getManager().persist(asiento);
	    return "siguiente";
	} else if (viaje.getAvailablePax() == 0)
	    return "cupo";
	else {
	    Seat asiento = Factories.persistence.newSeatDao()
		    .findByUserAndTrip(idUsuario, idViaje);
	    asiento.setStatus(SeatStatus.ACCEPTED);
	    Jpa.getManager().merge(asiento);

	    viaje.setAvailablePax(viaje.getAvailablePax() - 1);
	    Jpa.getManager().merge(viaje);
	    return "siguiente";
	}
    }

    private boolean exitePasajero(Long tripId, Long userId) {
	// TODO Auto-generated method stub
	return Factories.persistence.newSeatDao().findByUserAndTrip(userId,
		tripId) != null;
    }

    private Seat crearAsiento(Long idUsuario, Long idViaje) {
	Seat asiento = new Seat();
	asiento.setComment("");
	asiento.setStatus(SeatStatus.ACCEPTED);
	asiento.setTripId(idViaje);
	asiento.setUserId(idUsuario);
	return asiento;
    }

}
