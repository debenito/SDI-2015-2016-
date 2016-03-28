package uo.sdi.bussines;

import java.util.List;

import uo.sdi.model.Application;

public interface ApplicationService {

    String apuntarse(Long tripId, Long userId);

    void cancelarPlaza(Long idViaje, Long idUsuario);

    List<Application> usuariosPendientes(Long idViaje);

    void ponerExcluido(Long idViaje, Long idUsuario);

    String confirmarPlaza(Long idViaje, Long idUsuario);
}
