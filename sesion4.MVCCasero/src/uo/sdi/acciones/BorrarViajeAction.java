package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.acciones.logic.UsuarioLogica;
import uo.sdi.acciones.logic.ViajeLogica;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import alb.util.log.Log;

public class BorrarViajeAction implements Accion {
    // Logica del usuario
    UsuarioLogica logicaUsuario = LogicaFactory.nuevoUsuario();

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	// Parametros de inicializacion
	Long id = Long.parseLong (request.getParameter("id"));
	 ViajeLogica logicaViajes = LogicaFactory.nuevoViaje();
	 HttpSession session = request.getSession();
	 User user = (User) session.getAttribute("user");
	try {
	   logicaViajes.borrarViaje(id);
	   List<Trip>viajesPromotor = logicaViajes.listarViajesPromotor(user.getId());
	    request.setAttribute("viajesPromotor", viajesPromotor);
	    Log.debug("Obtenida lista de viajes del promotor conteniendo [%d] viajes",
		    viajesPromotor.size());
	} catch (Exception e) {
	    Log.error("Algo ha ocurrido registrar viaje");
	    e.getMessage();
	    String base = "Base de Datos Cerrada";
	    request.getServletContext().setAttribute("baseDatos", base);

	}
	return "EXITO";
    }

    

    @Override
    public String toString() {
	return getClass().getName();
    }

}
