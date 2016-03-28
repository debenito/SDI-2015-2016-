package uo.sdi.bussines.impl;

import java.util.List;

import uo.sdi.bussines.ApplicationService;
import uo.sdi.bussines.impl.classes.application.ApplicacionAlta;
import uo.sdi.bussines.impl.classes.application.ApplicacionBaja;
import uo.sdi.bussines.impl.classes.application.ApplicacionListado;
import uo.sdi.model.Application;

public class SimpleApplicationService implements ApplicationService {


    @Override
    public String apuntarse(Long tripId, Long userId) {
    	 return new ApplicacionAlta().apuntarse(tripId, userId);
    }

    @Override
    public void cancelarPlaza(Long idViaje, Long idUsuario) {
    	new ApplicacionBaja().cancelarPlaza(idViaje, idUsuario);
    }

    @Override
    public List<Application> usuariosPendientes(Long idViaje) {
	// TODO Auto-generated method stub
	return new ApplicacionListado().usuariosPendientes(idViaje);
    }

    @Override
    public void ponerExcluido(Long idViaje, Long idUsuario) {
	new ApplicacionBaja().ponerExcluido(idViaje, idUsuario);

    }

    @Override
    public String confirmarPlaza(Long idViaje, Long idUsuario) {
	
	return new ApplicacionAlta().confirmarPlaza(idViaje, idUsuario);
    }

   

}
