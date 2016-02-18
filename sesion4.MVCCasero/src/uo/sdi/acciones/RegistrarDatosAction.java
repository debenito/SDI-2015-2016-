package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.User;
import uo.sdi.model.UserStatus;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.UserDao;
import alb.util.log.Log;

public class RegistrarDatosAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		
	
		User usuario =registrarUserPublico(request);
		
		try {
			if(usuario !=null){
			UserDao dao = PersistenceFactory.newUserDao();
			dao.save(usuario);
			Log.debug("Registrado el usuario [%s] con exito",
					usuario.getLogin());
			request.setAttribute("mensajeError", "Exito al introducir el usuario");
			request.getServletContext().setAttribute("baseDatos","Abierta");
			}
		} catch (Exception e) {
			Log.error("Algo ha ocurrido registrado al usuario [%s]",
					usuario.getLogin());
			e.getMessage();
			String base ="Base de Datos Cerrada";
			request.getServletContext().setAttribute("baseDatos",base);
			
		}
		return "EXITO";
	}

	private User registrarUserPublico(HttpServletRequest request) {
		String login = request.getParameter("login");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String nuevoEmail = request.getParameter("email");
		String pass1 = request.getParameter("pass1");
		String pass2 = request.getParameter("pass2");
		if (comprobacionPassIguales(pass1, pass2)) {
		User usuario = new User();
			usuario.setLogin(login);
			usuario.setName(nombre);
			usuario.setSurname(apellidos);
			usuario.setPassword(pass1);
			usuario.setEmail(nuevoEmail);
			usuario.setStatus(UserStatus.CANCELLED);
			return usuario;
		}else{
		request.setAttribute("mensajeError",
				"Contraseñas diferentes Contraseña1 :"+pass1+" Contraseña2 :" +pass2);
		return null;
		}
	}

	private boolean comprobacionPassIguales(String pass1, String pass2) {
		if (pass1.equals(pass2)) {
			Log.info("Las contraseñas son iguales");
			return true;
		}else{
		Log.error("Contraseñas diferentes Contraseña1 : %s Contraseña2 : %s",
				pass1, pass2);
		return false;
		}
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
