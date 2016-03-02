package uo.sdi.acciones.gestionReservas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.acciones.Accion;
import uo.sdi.acciones.LogicaFactory;
import uo.sdi.acciones.logic.ApplicationLogica;

public class ExcluirAction implements Accion{

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	Long idViaje = Long.parseLong(request.getParameter("idTrip"));
	Long idUsuario = Long.parseLong(request.getParameter("idUser"));
	ApplicationLogica logicaApplication = LogicaFactory.nuevoApplication();
	try{
	    logicaApplication.ponerExcluido(idViaje,idUsuario);
	    Log.debug("Usuario excluido correctamete");
	}catch(Exception e){
	    Log.error("Algo ha ocurrido registrar viaje", e.getMessage());
	    e.getMessage();
	}
	
	return "EXITO";
    }

}
