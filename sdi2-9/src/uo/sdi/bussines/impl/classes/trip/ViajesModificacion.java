package uo.sdi.bussines.impl.classes.trip;

import java.util.Date;

import uo.sdi.infraestructure.Factories;
import uo.sdi.model.Trip;

public class ViajesModificacion {

	public void update(Trip trip){
		Factories.persistence.newTripDao().update(trip);
	}
	
	public void updateDone(Date hoy){
		Factories.persistence.newTripDao().updateDone(hoy);
	}
}
