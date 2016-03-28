package uo.sdi.bussines.impl.classes.trip;

import uo.sdi.infraestructure.Factories;

public class ViajeBaja {
public void updateCancel(Long id){
	Factories.persistence.newTripDao().updateCancel(id);
}
}
