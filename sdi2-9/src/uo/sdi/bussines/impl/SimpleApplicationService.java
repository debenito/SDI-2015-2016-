package uo.sdi.bussines.impl;

import java.util.List;

import uo.sdi.bussines.ApplicationService;
import uo.sdi.bussines.CommandExecutor;
import uo.sdi.bussines.impl.classes.application.ApplicacionBaja;
import uo.sdi.bussines.impl.classes.application.ApplicacionListado;
import uo.sdi.bussines.impl.classes.application.Apuntarse;
import uo.sdi.bussines.impl.classes.application.ConfirmarPlaza;
import uo.sdi.model.Application;

public class SimpleApplicationService implements ApplicationService {
    CommandExecutor executor = new CommandExecutor();

    @Override
    public String apuntarse(Long tripId, Long userId) {
	return (String) executor.execute(new Apuntarse(tripId, userId));
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

	return (String) executor
		.execute(new ConfirmarPlaza(idViaje, idUsuario));
    }

}
