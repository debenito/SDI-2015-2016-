package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.acciones.logic.ViajeLogica;
import uo.sdi.model.Rating;
import uo.sdi.model.Trip;
import alb.util.log.Log;

public class ListarViajesAction implements Accion {
    // Logica Viajes
    ViajeLogica logicaViajes = LogicaFactory.nuevoViaje();

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {

	List<Trip> viajes;
	HttpSession session = request.getSession();
	String base = "Base de Datos Cerrada";
	try {
	    viajes = logicaViajes.listarViajes();
	    request.setAttribute("listaViajes", viajes);
	    Log.debug("Obtenida lista de viajes conteniendo [%d] viajes",
		    viajes.size());
	    if(session.getAttribute("user")!=null){
		 List<Rating> listaViajesRegistrado = logicaViajes.listarViajesRegistrado();
		 request.setAttribute("listaViajesRegistrado", listaViajesRegistrado);
		    Log.debug("Obtenida lista de viajes del Usuario Registrado conteniendo [%d] viajes",
			    listaViajesRegistrado.size());
	    }
	    request.getServletContext().setAttribute("baseDatos", "Abierta");
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
