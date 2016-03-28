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

    List<Trip> listaViajesParticipacionPromotor(Long id);

    List<Trip> listaViajesParticipacionPendientes(Long id);

    List<Trip> listaViajesParticipacionSinPlaza(Long id);

    List<Trip> listaViajesParticipacionAdmitido(Long id);

    List<Trip> listaViajesParticipacionExcluido(Long id);

    List<Trip> listarViajesActivosPromotor(Date hoy, Long id);
}
