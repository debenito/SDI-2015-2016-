package uo.sdi.acciones.logic;

import java.util.List;

import uo.sdi.model.Trip;

public interface ViajeLogica {
    List<Trip> listarViajesActivos();

    List<Trip> listarViajesPromotor(Long id);
}
