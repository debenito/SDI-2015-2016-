package uo.sdi.bussines.impl.classes.trip;

import uo.sdi.bussines.Command;
import uo.sdi.model.Trip;
import uo.sdi.model.types.TripStatus;
import uo.sdi.persistence.util.Jpa;

public class CancelarViaje implements Command {
    private Long id;

    public CancelarViaje(Long id) {
	this.id = id;
    }

    @Override
    public Object execute() {
	// TODO Auto-generated method stub
	Trip trip = new TripFinder().findById(id);
	trip.setStatus(TripStatus.CANCELLED);
	Jpa.getManager().merge(trip);
	return null;
    }

}
