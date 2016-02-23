package uo.sdi.acciones.impl;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.acciones.logic.ViajeLogica;
import uo.sdi.model.Rating;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.RatingDao;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.TripDao;

public class ViajeLogic implements ViajeLogica {
    private TripDao dao = PersistenceFactory.newTripDao();
    private SeatDao daoSeat = PersistenceFactory.newSeatDao();
    private RatingDao daoRating = PersistenceFactory.newRatingDao();

    @Override
    public List<Trip> listarViajes() {
	
	return dao.findAll();
    }

    @Override
    public List<Rating> listarViajesRegistrado() {
	List<Trip> listaViajes = listarViajes();
	List<Rating> rating = new ArrayList<Rating>() ;
	for (Trip trip : listaViajes) {
	    List<Seat> asientos=daoSeat.findByTrip(trip.getId());
	    for (Seat seat : asientos) {
		if( trip.getPromoterId().longValue()!= seat.getUserId().longValue())
		rating.add(daoRating.findByAboutFrom(trip.getPromoterId(), seat.getTripId(), seat.getUserId(), seat.getTripId()));
	    }
	}
	return rating;
    }

}
