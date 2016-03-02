package uo.sdi.acciones.impl;

import java.util.List;

import uo.sdi.acciones.logic.ApplicationLogica;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.TripDao;

public class ApplicationLogic implements ApplicationLogica{
    private ApplicationDao dao = PersistenceFactory.newApplicationDao();
    private SeatDao daoSeat = PersistenceFactory.newSeatDao();
    private TripDao daoTrip = PersistenceFactory.newTripDao();

    @Override
    public void apuntarse(Long tripId, Long userId) {
	Application p = new Application();
	p.setTripId(tripId);
	p.setUserId(userId);
	dao.save(p);
	
    }



    @Override
    public void cancelarPlaza(Long idViaje, Long idUsuario) {
	Long id[]={idUsuario,idViaje};
	dao.delete(id);
	
	if(daoSeat.findById(id) != null){ 
	    daoSeat.delete(id);
	}
	
    }



    @Override
    public List<Application> usuariosPendientes(Long idViaje) {
	// TODO Auto-generated method stub
	return dao.findByTripId(idViaje);
    }



    @Override
    public void ponerExcluido(Long idViaje, Long idUsuario) {
	Long id[]={idUsuario,idViaje};
	Seat asiento =daoSeat.findById(id);
	 Trip viaje = daoTrip.findById(idViaje);
	 viaje.setAvailablePax(viaje.getAvailablePax()+1);
	   daoTrip.update(viaje);
	asiento.setStatus(SeatStatus.EXCLUDED);
	daoSeat.update(asiento);
	
    }



    @Override
    public String confirmarPlaza(Long idViaje, Long idUsuario) {
	    Trip viaje = daoTrip.findById(idViaje);
	    if(viaje.getAvailablePax()!=0){
	    viaje.setAvailablePax(viaje.getAvailablePax()-1);
	    daoTrip.update(viaje);
	   Seat asiento = crearAsiento(idUsuario,idViaje);
	    daoSeat.save(asiento);
	    return "siguiente";
	    }else
		return "cupo";
	}



    private Seat crearAsiento(Long idUsuario,Long idViaje) {
	 Seat asiento = new Seat();
	 asiento.setComment("");
	 asiento.setStatus(SeatStatus.ACCEPTED);
	 asiento.setTripId(idViaje);
	 asiento.setUserId(idUsuario);
	return asiento;
    }
	
    }


