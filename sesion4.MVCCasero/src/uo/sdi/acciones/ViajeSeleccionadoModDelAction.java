package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.acciones.logic.ViajeLogica;
import uo.sdi.model.Trip;
import alb.util.log.Log;

public class ViajeSeleccionadoModDelAction implements Accion {
    // Logica Viajes
    ViajeLogica logicaViajes = LogicaFactory.nuevoViaje();
    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	String id = request.getParameter("id");
	
	String base = "Base de Datos Cerrada";
	try {
	   Trip viaje = logicaViajes.buscarViajeId(Long.parseLong(id));

	    Log.debug("Obtenido  viaje con id [%d] viajes",viaje.getId());
	    request.setAttribute("viajes", viaje);
	    
	    request.getServletContext().setAttribute("baseDatos", "Abierta");
	
	} catch (Exception e) {
	    Log.error("Algo ha ocurrido obteniendo el viaje");
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
