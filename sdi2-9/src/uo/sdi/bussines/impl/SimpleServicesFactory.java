package uo.sdi.bussines.impl;

import uo.sdi.bussines.ApplicationService;
import uo.sdi.bussines.RatingService;
import uo.sdi.bussines.ServicesFactory;
import uo.sdi.bussines.UsuarioService;
import uo.sdi.bussines.ViajeService;

public class SimpleServicesFactory implements ServicesFactory {

	@Override
	public UsuarioService nuevoUsuario() {
		// TODO Auto-generated method stub
		return new SimpleUsuarioService();
	}

	@Override
	public ViajeService nuevoViaje() {
		// TODO Auto-generated method stub
		return  new SimpleViajeService();
	}

	@Override
	public RatingService nuevoRating() {
		// TODO Auto-generated method stub
		return  new SimpleRatingService();
	}

	@Override
	public ApplicationService nuevoApplication() {
		// TODO Auto-generated method stub
		return  new SimpleApplicationService();
	}

	

}
