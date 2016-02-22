package uo.sdi.acciones.impl;

import java.util.List;

import uo.sdi.acciones.logic.ViajeLogica;
import uo.sdi.model.Trip;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;

public class ViajeLogic implements ViajeLogica {
    private TripDao dao = PersistenceFactory.newTripDao();

    @Override
    public List<Trip> listarViajes() {
	
	return dao.findAll();
    }

}
