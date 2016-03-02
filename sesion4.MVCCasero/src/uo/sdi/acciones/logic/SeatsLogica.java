package uo.sdi.acciones.logic;

import java.util.List;

import uo.sdi.model.Trip;
import uo.sdi.model.User;

public interface SeatsLogica {
    List<Trip> listaViajesParticipacionAdmitido(User usuario);

    List<Trip> listaViajesParticipacionExcluido(User usuario);
}
