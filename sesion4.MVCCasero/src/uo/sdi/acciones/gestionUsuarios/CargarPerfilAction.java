package uo.sdi.acciones.gestionUsuarios;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;
import alb.util.log.Logger;
import uo.sdi.acciones.Accion;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;

public class CargarPerfilAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	User user= null;
	Trip viaje= null;
	User promotor= null;
	User comenta=null;
	HttpSession session=request.getSession();
	try{
	    long id= Long.parseLong(request.getParameter("id"));
	    comenta=(User)session.getAttribute("user");
	    user=PersistenceFactory.newUserDao().findById(id);
	    viaje=PersistenceFactory.newTripDao().findById(Long.parseLong(request.getParameter("idViaje")));
	    promotor=PersistenceFactory.newUserDao().findById(viaje.getPromoterId());
	    request.setAttribute("usuario",user);
	    request.setAttribute("viaje",viaje);
	    request.setAttribute("promotor",promotor);
	    request.setAttribute("comenta",comenta);
	    Log.debug("Cargado el perfil del usuario"+ " "+ user.getId()+ " "+ promotor.getId()+" "+comenta.getId());
	}catch(Exception e){
	    Log.error("Error cargando el perfil del usuario");
	    e.getMessage();
	}
	return "EXITO";
    }

}
