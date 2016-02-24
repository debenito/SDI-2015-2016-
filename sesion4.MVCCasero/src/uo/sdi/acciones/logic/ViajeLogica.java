package uo.sdi.acciones.logic;

import java.util.List;

import uo.sdi.model.Rating;
import uo.sdi.model.Trip;

public interface ViajeLogica {
    List<Trip> listarViajes();

    List<Rating> listarViajesRegistrado();

    List<Trip> listarViajesPromotor(Long id);
}
