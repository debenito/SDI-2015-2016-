package com.sdi.acciones;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;

import com.sdi.util.*;

public abstract class ControlAbstract implements Accion {
    protected Connection con = null;
    protected String pasa = "";

    public abstract String control(HttpServletRequest request,
	    HttpServletResponse respones);

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	try {
	    con = Jdbc.getConnection();
	    pasa = executeAccion(request, response);
	} catch (SQLException e) {
	    Log.info("Problemas base de datos");
	    request.setAttribute("error", "Problemas base de datos");
	    pasa = "FRACASO";
	} finally {
	    try {
		con.close();
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	return pasa;
    }

    abstract String executeAccion(HttpServletRequest request,
	    HttpServletResponse response);

}