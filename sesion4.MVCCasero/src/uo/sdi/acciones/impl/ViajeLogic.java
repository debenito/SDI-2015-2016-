package uo.sdi.acciones.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import uo.sdi.acciones.logic.ViajeLogica;
import uo.sdi.model.Rating;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.RatingDao;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.TripDao;

public class ViajeLogic implements ViajeLogica {
    private TripDao dao = PersistenceFactory.newTripDao();
    private SeatDao daoSeat = PersistenceFactory.newSeatDao();
    private RatingDao daoRating = PersistenceFactory.newRatingDao();
    @SuppressWarnings("deprecation")
    Date hoy = new Date(Calendar.YEAR, Calendar.MONTH, Calendar.DATE,
	    Calendar.HOUR, Calendar.MINUTE, Calendar.SECOND);

    @Override
    public List<Trip> listarViajes() {

	List<Trip> viajes = dao.findAll();
	List<Trip> viajesFinal = new ArrayList<>();

	for (Trip trip : viajes) {
	    if (trip.getStatus().equals(TripStatus.OPEN)
		    && trip.getMaxPax() != 0
		    && hoy.after(trip.getClosingDate()))
		viajesFinal.add(trip);
	}
	return viajesFinal;
    }

    @Override
    public List<Rating> listarViajesRegistrado() {
	List<Trip> listaViajes = dao.findAll();
	List<Rating> rating = new ArrayList<Rating>();
	for (Trip trip : listaViajes) {
	    List<Seat> asientos = daoSeat.findByTrip(trip.getId());
	    if (trip.getStatus().equals(TripStatus.CANCELLED)
		    && trip.getStatus().equals(TripStatus.CLOSED))
		for (Seat seat : asientos) {
		    if (trip.getPromoterId().longValue() != seat.getUserId()
			    .longValue())
			rating.add(daoRating.findByAboutFrom(
				trip.getPromoterId(), seat.getTripId(),
				seat.getUserId(), seat.getTripId()));
		}
	}
	return rating;
    }

    @Override
    public List<Trip> listarViajesPromotor(Long id) {

	return null;
    }

}
