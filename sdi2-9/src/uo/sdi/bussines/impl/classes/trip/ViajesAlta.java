package uo.sdi.bussines.impl.classes.trip;

import uo.sdi.infraestructure.Factories;
import uo.sdi.model.Trip;

public class ViajesAlta {
public void save (Trip viaje){
	Factories.persistence.newTripDao().save(viaje);
}
public Trip buscarViajeId(Long l){
	return Factories.persistence.newTripDao().findById(l);
}
}
