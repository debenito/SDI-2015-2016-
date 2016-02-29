package com.sdi.acciones;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Contacto;
import com.sdi.model.Correo;
import com.sdi.model.Destinatario;
import com.sdi.model.Usuario;
import com.sdi.persistence.CorreoDao;
import com.sdi.persistence.DestinatarioDao;
import com.sdi.persistence.UsuarioDao;
import com.sdi.persistence.exception.AlreadyPersistedException;

public class AnadirUsuariosAction extends ControlAbstract {

    private DestinatarioDao des;
    private CorreoDao co;
    private UsuarioDao ud;
    private String cadena = "";
    private String asunto = "";
    private String cuerpo = "";

    @Override
    public String control(HttpServletRequest request,
	    HttpServletResponse respones) {
	String pasa = "EXITO";
	ud = Factories.persistence.createUsuarioDao();
	ud.setConection(con);
	List<Usuario> lista = ud.getUsuarios();
	HttpSession session = request.getSession();
	Usuario u = (Usuario) session.getAttribute("user");
	for (Usuario usuario : lista) {
	    if (usuario.getLogin().equals(u.getLogin()) && !usuario.isActivo()) {
		pasa = "FRACASO";
		Log.info("Usuario desactivado "
			+ "por el administrador se le cerrara la session");
		request.setAttribute("error", "Usuario desactivado"
			+ "por el administrador se le cerrara la session");
	    }
	}
	return pasa;

    }

    @SuppressWarnings("unchecked")
    private List<Contacto> cortaCadena(String para, HttpSession session,
	    HttpServletRequest request) {
	List<Contacto> c = new ArrayList<Contacto>();
	List<Contacto> contacto = (List<Contacto>) session
		.getAttribute("listaContactos");
	List<Contacto> privada = (List<Contacto>) session
		.getAttribute("listaPrivada");
	String[] cadena = para.split(";");
	for (int i = 0; i < cadena.length; i++) {
	    for (Contacto co : contacto) {
		if (cadena[i].equals(co.getEmail()))
		    c.add(co);
	    }
	    for (Contacto co2 : privada) {
		if (cadena[i].equals(co2.getEmail()))
		    c.add(co2);
	    }
	}
	return c;
    }

    @SuppressWarnings("unused")
    @Override
    String executeAccion(HttpServletRequest request,
	    HttpServletResponse response) {
	String pasa = "EXITO";
	HttpSession session = request.getSession();
	String fecha = request.getParameter("fecha");
	String para = request.getParameter("para");

	if (request.getParameter("asunto") != null)
	    asunto += request.getParameter("asunto");
	if (request.getParameter("cuerpo") != null)
	    cuerpo += request.getParameter("cuerpo");

	Usuario u = (Usuario) session.getAttribute("user");
	String activo = null;
	String activo1 = null;
	pasa = control(request, response);
	if (pasa.equals("EXITO")) {

	    if (request.getParameter("lista1") != null) {

		if (request.getParameter("lista1").equals("vacio1")) {
		    cadena += "";
		} else {
		    activo = (String) request.getParameter("lista1");
		    cadena += activo + ";";
		}
	    }

	    if (request.getParameter("lista2") != null) {

		if (request.getParameter("lista2").equals("vacio2")) {
		    cadena += "";
		} else {
		    activo1 = (String) request.getParameter("lista2");
		    cadena += activo1 + "";
		}

	    }
	    request.setAttribute("cadena", cadena);
	    request.setAttribute("cuerpo", cuerpo);
	    request.setAttribute("asunto", asunto);
	}
	Correo c = new Correo();
	Destinatario de = new Destinatario();
	c.setAsunto(asunto);
	c.setCuerpo(cuerpo);
	c.setFechahora(Calendar.DATE);
	c.setLogin_Usuario(u.getLogin());
	c.setCarpeta(2);
	try {
	    co = Factories.persistence.createCorreoDao();
	    co.setConection(con);
	    int id = co.save(c);
	    List<Contacto> conta = cortaCadena(cadena, session, request);
	    for (Contacto contacto : conta) {
		de.setId_Contacto(contacto.getId());
		de.setId_Correo(id);
		des = Factories.persistence.createDestinatarioDao();
		des.setConection(con);
		des.save(de);
		Log.info("Mensaje guardado con exito en el borrador ");
		request.setAttribute("exito",
			"Su correo ha sido guardado en el borrador "
				+ "si desea modificarlo vaya a la lista de "
				+ "  borradores y seleccionelo");
	    }
	} catch (AlreadyPersistedException e) {
	    Log.error("Algo ha ocurrido al guardar el correo en el borrador ");
	    request.setAttribute("error",
		    "Algo ha ocurrido al guardar el correo en el borrador");
	    pasa = "FRACASO";
	}

	return pasa;

    }

}
