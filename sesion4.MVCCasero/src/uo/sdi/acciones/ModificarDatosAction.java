package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.UserDao;
import alb.util.log.Log;

public class ModificarDatosAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {

	HttpSession session = request.getSession();
	User usuario = ((User) session.getAttribute("user"));
	String passNueva = request.getParameter("pass");
	String passVieja = request.getParameter("passVieja");
	String passNueva1 = request.getParameter("pass1");
	String nombre = request.getParameter("name");
	String apellidos = request.getParameter("surname");
	String email = request.getParameter("email");
	String login = request.getParameter("login");
	UserDao dao = PersistenceFactory.newUserDao();
	try {
	    if (!comprobacionContraseñasIguales(passNueva, passNueva1)) {
		request.setAttribute("mensajeError",
			"Contraseñas diferentes contraseña 1 :" + passNueva
				+ " Contraseña 2 :" + passNueva1);
		Log.error(
			"Contraseñas diferentes contraseña 1 :[%s] Contraseña 2 : [%s]",
			passNueva, passNueva1);
	    }
	    else if (comprobacionContraseñaVieja(dao, passVieja, usuario.getId())) {
		usuario.setEmail(email);
		usuario.setLogin(login);
		usuario.setName(nombre);
		usuario.setPassword(passNueva);
		usuario.setSurname(apellidos);
		dao.update(usuario);
		Log.debug("Modificado usuario [%s] con el valor",
			usuario.getLogin());
	    } else {
		request.setAttribute("mensajeError",
			"Contraseña vieja no coincide o alguna de ellas esta vacia");
		Log.debug(
			"Contraseña vieja de usuario [%s] no coincide con la antigua"
				+ "[%s]", usuario.getLogin(), passVieja);
	    }

	} catch (Exception e) {
	    Log.error("Algo ha ocurrido actualizando el email de [%s]",
		    usuario.getLogin());
	    e.getMessage();
	    String base = "Base de Datos Cerrada";
	    request.getServletContext().setAttribute("baseDatos", base);
	}
	return "EXITO";
    }

    private boolean comprobacionContraseñasIguales(String passNueva,
	    String passNueva1) {

	return passNueva.equals(passNueva1);
    }

    private boolean comprobacionContraseñaVieja(UserDao dao, String passVieja,
	    long id) {
	User u = dao.findById(id);
	return passVieja.equals(u.getPassword()) && passVieja != "";
    }

    @Override
    public String toString() {
	return getClass().getName();
    }

}
