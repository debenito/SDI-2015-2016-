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

public class NuevoCorreoAction extends ControlAbstract {
    private CorreoDao co;
    private UsuarioDao ud;
    private DestinatarioDao des;

    private List<Contacto> cortaCadena(String para, HttpSession session,
	    HttpServletRequest request) {
	List<Contacto> c = new ArrayList<Contacto>();
	@SuppressWarnings("unchecked")
	List<Contacto> contacto = (List<Contacto>) session
		.getAttribute("listaContactos");
	@SuppressWarnings("unchecked")
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

    @Override
    public String control(HttpServletRequest request,
	    HttpServletResponse respones) {
	String pasa = "EXITO";
	ud = Factories.persistence.createUsuarioDao();
	ud.setConection(con);
	;
	List<Usuario> lista = ud.getUsuarios();
	HttpSession session = request.getSession();
	Usuario u = (Usuario) session.getAttribute("user");
	for (Usuario usuario : lista) {
	    if (usuario.getLogin().equals(u.getLogin()) && !usuario.isActivo()) {
		pasa = "FRACASO";
		Log.info("Usuario desactivado "
			+ "por el administrador se le cerrara la session");
		request.setAttribute("error", "Usuario desactivado "
			+ "por el administrador se le cerrara la session");
	    }
	}
	return pasa;
    }

    @SuppressWarnings("unused")
    @Override
    String executeAccion(HttpServletRequest request,
	    HttpServletResponse response) {
	String pasa = "EXITO";
	HttpSession session = request.getSession();
	String fecha = request.getParameter("fecha");
	String para = request.getParameter("para");
	String asunto = request.getParameter("asunto");
	String cuerpo = request.getParameter("cuerpo");
	Usuario u = (Usuario) session.getAttribute("user");
	pasa = control(request, response);
	if (pasa.equals("EXITO")) {
	    Correo c = new Correo();
	    Destinatario de = new Destinatario();
	    c.setAsunto(asunto);
	    c.setCuerpo(cuerpo);
	    c.setFechahora(Calendar.DATE);
	    c.setLogin_Usuario(u.getLogin());
	    c.setCarpeta(1);
	    try {
		co = Factories.persistence.createCorreoDao();
		co.setConection(con);
		;
		int id = co.save(c);
		List<Contacto> conta = cortaCadena(para, session, request);
		for (Contacto contacto : conta) {
		    de.setId_Contacto(contacto.getId());
		    de.setId_Correo(id);
		    des = Factories.persistence.createDestinatarioDao();
		    des.setConection(con);
		    ;
		    des.save(de);

		}
		Log.info("Mensaje enviado ");
		request.setAttribute("exito", "Su correo ha sido enviado");
	    } catch (AlreadyPersistedException e) {
		Log.error("Algo ha ocurrido  "
			+ "al guardar el correo en el borrador");
		request.setAttribute("error",
			"Algo ha ocurrido al guardar el correo en el borrador");
		pasa = "FRACASO";
	    }

	}

	return pasa;
    }

}
