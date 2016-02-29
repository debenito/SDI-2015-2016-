package uo.sdi.persistence;

import java.util.Date;
import java.util.List;

import uo.sdi.model.Trip;
import uo.sdi.persistence.util.GenericDao;

public interface TripDao extends GenericDao<Trip, Long> {

	Trip findByPromoterIdAndArrivalDate(Long id, Date arrivalDate);
	List<Trip> listarViajesActivos(Date d);
	List<Trip> listarViajesPromotor(Long id);
	int updateCancel(Long id);
	int updateDone(Date hoy);
}
