package uo.sdi.bussines.impl.classes.trip;

import uo.sdi.bussines.Command;
import uo.sdi.model.Trip;
import uo.sdi.persistence.util.Jpa;

public class RegistrarViaje implements Command {
    private Trip trip;

    public RegistrarViaje(Trip trip) {
	this.trip = trip;
    }

    @Override
    public Object execute() {

	Jpa.getManager().persist(trip);
	return trip;
    }

}
