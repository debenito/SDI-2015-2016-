package uo.sdi.acciones.listados;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.acciones.Accion;
import uo.sdi.acciones.LogicaFactory;
import uo.sdi.acciones.logic.ApplicationLogica;
import uo.sdi.model.Application;
import alb.util.log.Log;

public class ListadoUsuariosPendientesAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	Long idViaje = Long.parseLong(request.getParameter("id"));
	ApplicationLogica logicaApplication = LogicaFactory.nuevoApplication();
	try {
	   List<Application> listausuariosPendientes= logicaApplication.usuariosPendientes(idViaje);
	   request.setAttribute("listausuariosPendientes", listausuariosPendientes);
	    Log.debug("Usuario eliminado/confirmado correctamete");
	} catch (Exception e) {
	    Log.error("Algo ha ocurrido registrar viaje");
	    e.getMessage();
	    String base = "Base de Datos Cerrada";
	    request.getServletContext().setAttribute("baseDatos", base);

	}
	return "EXITO";

    }

}
