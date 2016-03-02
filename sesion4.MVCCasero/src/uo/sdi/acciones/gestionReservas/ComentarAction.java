package uo.sdi.acciones.gestionReservas;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;
import uo.sdi.acciones.Accion;
import uo.sdi.model.Rating;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;

public class ComentarAction implements Accion{

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	// TODO Auto-generated method stub
	
	
	Rating rating=new Rating();
	HttpSession session= request.getSession();
	User usuario= (User)session.getAttribute("user");
	User comentado= PersistenceFactory.newUserDao().findById(Long.parseLong(request.getParameter("userId")));
	 long id=Long.parseLong(request.getParameter("id"));
	    String comment= request.getParameter("comentarios");
	    String value= request.getParameter("puntuacion");
	try{
	   
	  
	    rating.setSeatAboutUserId(comentado.getId());
	    rating.setValue(Integer.parseInt(value));
	    rating.setSeatFromTripId(id);
	    rating.setSeatFromUserId(usuario.getId());
	    rating.setSeatAboutTripId(id);
	    rating.setComment(comment);
	    PersistenceFactory.newRatingDao().save(rating);
	    Log.debug("Viaje comentado con exito");
	}catch(Exception e){
	    Log.error("Algo ha ocurrido comentando el viaje"+comment+" "+id+" "+usuario.getId()+" "+value);
	    e.getMessage();
	}
	return "EXITO";
    }
    
    

}
