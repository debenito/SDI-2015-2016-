package uo.sdi.bussines.impl.classes.application;

import uo.sdi.infraestructure.Factories;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.types.SeatStatus;

public class ApplicacionBaja {

    public void cancelarPlaza(Long idViaje, Long idUsuario) {
	Long id[] = { idUsuario, idViaje };
	Factories.persistence.newApplicationDao().delete(id);

	if (Factories.persistence.newSeatDao().findById(id) != null) {
	    Factories.persistence.newSeatDao().delete(id);
	}

    }

    public void ponerExcluido(Long idViaje, Long idUsuario) {
	Long id[] = { idUsuario, idViaje };
	Seat asiento = Factories.persistence.newSeatDao().findById(id);
	Trip viaje = Factories.persistence.newTripDao().findById(idViaje);
	if (viaje.getMaxPax() > viaje.getAvailablePax())
	    viaje.setAvailablePax(viaje.getAvailablePax() + 1);
	Factories.persistence.newTripDao().update(viaje);
	asiento.setStatus(SeatStatus.EXCLUDED);
	Factories.persistence.newSeatDao().update(asiento);

    }
}
