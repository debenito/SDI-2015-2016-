package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Trip;
import uo.sdi.persistence.PersistenceFactory;
import alb.util.log.Log;

public class ListarViajesAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		List<Trip> viajes;
		String base ="Base de Datos Cerrada";
		try {
			viajes=PersistenceFactory.newTripDao().findAll();
			request.setAttribute("listaViajes", viajes);
			Log.debug("Obtenida lista de viajes conteniendo [%d] viajes", viajes.size());
			request.getServletContext().setAttribute("baseDatos","Abierta");
		}
		catch (Exception e) {
			Log.error("Algo ha ocurrido obteniendo lista de viajes");
			e.getMessage();
			
			request.getServletContext().setAttribute("baseDatos",base);
		}
		return "EXITO";
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
