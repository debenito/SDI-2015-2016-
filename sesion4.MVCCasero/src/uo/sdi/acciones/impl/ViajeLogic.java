package uo.sdi.acciones.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import uo.sdi.acciones.logic.ViajeLogica;
import uo.sdi.model.Trip;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;

public class ViajeLogic implements ViajeLogica {
    private TripDao dao = PersistenceFactory.newTripDao();
    @SuppressWarnings("deprecation")
    Date hoy = new Date(Calendar.YEAR, Calendar.MONTH, Calendar.DATE,
	    Calendar.HOUR, Calendar.MINUTE, Calendar.SECOND);

    @Override
    public List<Trip> listarViajesActivos() {
	return dao.listarViajesActivos(hoy);
    }

   
    @Override
    public List<Trip> listarViajesPromotor(Long id) {

	return dao.listarViajesPromotor(id);
    }

}
