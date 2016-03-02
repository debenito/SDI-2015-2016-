package uo.sdi.acciones.listados;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.acciones.Accion;
import uo.sdi.acciones.LogicaFactory;
import uo.sdi.acciones.logic.ViajeLogica;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import alb.util.log.Log;

public class ViajesParticipadosAction implements Accion {
    // Logica Viajes
    ViajeLogica logicaViajes = LogicaFactory.nuevoViaje();
  

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	

	List<Trip> viajesPromotor;
	List<Trip> viajesPendientes;
	List<Trip> viajesSinPlaza;
	List<Trip> viajesAdmitido;
	List<Trip> viajesExcluido;
	HttpSession session = request.getSession();
	User usuario = (User) session.getAttribute("user");
	String base = "Base de Datos Cerrada";
	try {
	    viajesPromotor = logicaViajes.listaViajesParticipacionPromotor(usuario);
	    viajesPendientes= logicaViajes.listaViajesParticipacionPendientes(usuario);
	    viajesSinPlaza = logicaViajes.listaViajesParticipacionSinPlaza(usuario);
	    viajesAdmitido = logicaViajes.listaViajesParticipacionAdmitido(usuario);
	    viajesExcluido = logicaViajes.listaViajesParticipacionExcluido(usuario);
	   request.setAttribute("viajesPromotor", viajesPromotor);
	   request.setAttribute("viajesPendientes", viajesPendientes);
	   request.setAttribute("viajesSinPlaza", viajesSinPlaza);
	   request.setAttribute("viajesAdmitido", viajesAdmitido);
	   request.setAttribute("viajesExcluido", viajesExcluido);
		Log.debug(
			"Obtenidas las listas de participacion en viajes");
	} catch (Exception e) {
	    Log.error("Algo ha ocurrido obteniendo lista de viajes");
	    e.getMessage();

	    request.getServletContext().setAttribute("baseDatos", base);
	}
	return "EXITO";
    }

    @Override
    public String toString() {
	return getClass().getName();
    }

}
