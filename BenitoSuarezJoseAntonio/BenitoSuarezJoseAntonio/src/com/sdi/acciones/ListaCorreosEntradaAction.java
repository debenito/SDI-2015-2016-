package com.sdi.acciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Contacto;
import com.sdi.model.Correo;
import com.sdi.model.Usuario;
import com.sdi.persistence.CorreoDao;
import com.sdi.persistence.UsuarioDao;

public class ListaCorreosEntradaAction extends ControlAbstract {
    private CorreoDao co;
    private UsuarioDao ud;

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
		request.setAttribute("error", "Usuario desactivado"
			+ "por el administrador se le cerrara la session");
	    }
	}
	return pasa;
    }

    @Override
    String executeAccion(HttpServletRequest request,
	    HttpServletResponse response) {
	String pasa = "EXITO";
	pasa = control(request, response);
	if (pasa.equals("EXITO")) {
	    HttpSession session = request.getSession();
	    List<Correo> usuarios;
	    List<Correo> bandejaentrada = new ArrayList<Correo>();
	    List<Contacto> destinatorios = null;
	    Map<Integer, List<Contacto>> desti = new HashMap<>();
	    ;
	    Map<Integer, Contacto> primero = new HashMap<>();
	    Usuario u = (Usuario) session.getAttribute("user");
	    try {
		co = Factories.persistence.createCorreoDao();
		co.setConection(con);
		;
		usuarios = co.getCorreos();
		for (Correo correo : usuarios) {
		    if (correo.getCarpeta() == 1
			    && correo.getLogin_Usuario().equals(u.getLogin())) {
			bandejaentrada.add(correo);

			destinatorios = co.getDestinatariosCorreo(correo
				.getId());
			if (destinatorios.size() > 0) {

			    desti.put(correo.getId(), destinatorios);

			    primero.put(correo.getId(), destinatorios.get(0));
			}
		    }
		}
		session.setAttribute("destinatarios", desti);
		session.setAttribute("primero", primero);
		session.setAttribute("listaCorreosEntrada", bandejaentrada);

		Log.debug(
			"Obtenida lista de correos conteniendo [%d] usuarios",
			usuarios.size());

	    } catch (Exception e) {
		Log.error("Algo ha ocurrido obteniendo lista de correos");
		request.setAttribute("error",
			"Algo ha ocurrido obteniendo lista de correos");
		pasa = "FRACASO";
	    }
	}
	return pasa;

    }
}
