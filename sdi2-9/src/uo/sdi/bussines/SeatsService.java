package uo.sdi.bussines;

import java.util.List;

import uo.sdi.model.Trip;
import uo.sdi.model.User;

public interface SeatsService {
    List<Trip> listaViajesParticipacionAdmitido(User usuario);

    List<Trip> listaViajesParticipacionExcluido(User usuario);
}
