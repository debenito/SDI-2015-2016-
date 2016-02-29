package com.sdi.acciones;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;

public class LogoutAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	String pasa = "EXITO";
	HttpSession session = request.getSession();
	session.invalidate();
	try {
	    response.sendRedirect("login.jsp");
	} catch (IOException e) {
	    Log.info("Problemas al cerrar la sesion ", session);
	    request.setAttribute("error", "Problemas al cerrar la sesion ");
	    pasa = "FRACASO";
	    e.printStackTrace();
	}

	return pasa;
    }

}
