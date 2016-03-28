package uo.sdi.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import uo.sdi.infraestructure.Factories;
import uo.sdi.model.User;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 55555L;

	private String username;
	private String password;

	private User user;

	private String deactivated;

	public LoginBean() {
		this.username = "";
		this.password = "";
		this.deactivated = "";
	}

	public String getDeactivated() {
		return deactivated;
	}

	public void setDeactivated(String deactivated) {
		this.deactivated = deactivated;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Método encargado de almacenar en sesión el usuario que acaba de realizar
	 * el logeo
	 * 
	 * @return Nos lleva a la vista princpal.xhtml desde la vista login.xhtml
	 */
	public String login() {

		String salida = "login";

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		session.removeAttribute("error");
		ResourceBundle bundle = FacesContext.getCurrentInstance()
				.getApplication()
				.getResourceBundle(FacesContext.getCurrentInstance(), "msgs");

		User usuario = null;

		if (session.isNew() || session.getAttribute("user") == null) {
			// Uso de la conexión para la creación del DAO

			
			usuario =Factories.services.nuevoUsuario().validacionUsuario(username, password) ;

			session.setAttribute("user", usuario);
			this.user = usuario;

			salida = "index";

		}
		return salida;	}
}