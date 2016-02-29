package com.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Contacto;
import com.sdi.model.Usuario;
import com.sdi.persistence.ContactoDao;
import com.sdi.persistence.UsuarioDao;

public class ListarContactosUsuarioAction extends ControlAbstract {
    private ContactoDao co;
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
			+ " por el administrador se le cerrara la session");
		request.setAttribute("error", "Usuario desactivado"
			+ " por el administrador se le cerrara la session");
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
	    List<Contacto> usuarios;

	    List<Contacto> privada;
	    HttpSession session = request.getSession();
	    Usuario u = (Usuario) session.getAttribute("user");
	    try {
		co = Factories.persistence.createContactoDao();
		co.setConection(con);
		;
		usuarios = co.getAdminContactos();
		privada = co.getLoginContactos(u.getLogin());
		session.setAttribute("listaContactos", usuarios);
		session.setAttribute("listaPrivada", privada);

		Log.debug(
			"Obtenida lista de contactos conteniendo [%d] usuarios",
			usuarios.size());
		Log.debug(
			"Obtenida lista de contactos conteniendo [%d] usuarios",
			privada.size());

	    } catch (Exception e) {
		Log.error("Algo ha ocurrido obteniendo lista de usuarios");
		request.setAttribute("error",
			"Algo ha ocurrido obteniendo lista de contactos");
		pasa = "FRACASO";
	    }
	}
	return pasa;

    }

}
