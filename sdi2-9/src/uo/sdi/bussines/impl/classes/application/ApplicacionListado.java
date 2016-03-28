package uo.sdi.bussines.impl.classes.application;

import java.util.List;

import uo.sdi.infraestructure.Factories;
import uo.sdi.model.Application;

public class ApplicacionListado {
	 public List<Application> usuariosPendientes(Long idViaje) {
			// TODO Auto-generated method stub
			return Factories.persistence.newApplicationDao().findByTripId(idViaje);
		    }

}
