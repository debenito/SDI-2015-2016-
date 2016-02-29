package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;
import uo.sdi.model.Application;
import uo.sdi.model.User;
import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.PersistenceFactory;


public class ApuntarseAction implements Accion{

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	// TODO Auto-generated method stub
	HttpSession session= request.getSession();
	ApplicationDao apDao= PersistenceFactory.newApplicationDao();
	User user= (User)session.getAttribute("user");
	Application ap= apDao.findTripByUserId(user.getId(),Long.parseLong(request.getParameter("id")));
	try{
	    if(ap==null){
		ap=new Application();
		ap.setTripId(Long.parseLong(request.getParameter("id")));
		ap.setUserId(user.getId());
		apDao.save(ap);
		Log.debug("Registrada la solicitud del usuario [%s] para el viaje [%s] con exito",
			ap.getUserId(),ap.getTripId());
	    }
	}catch (Exception e){
	    Log.error("Error al registrar la solicitud del usuario [%s] para el viaje [%s] con exito",
			ap.getUserId(),ap.getTripId());
	}
	return "EXITO";
    }

}
