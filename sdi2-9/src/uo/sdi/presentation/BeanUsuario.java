package uo.sdi.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpSession;

import uo.sdi.infraestructure.Factories;
import uo.sdi.model.User;
import uo.sdi.model.types.UserStatus;

@ManagedBean(name = "controller")
@SessionScoped
public class BeanUsuario extends AbstractBean implements Serializable {
    private static final long serialVersionUID = 55555L;
    // Se aÃ±ade este atributo de entidad para recibir el User concreto
    // selecionado de la tabla o de un formulario
    // Es necesario inicializarlo para que al entrar desde el formulario de
    // AltaForm.xml se puedan
    // dejar los avalores en un objeto existente.
    private User User = new User();
    private ResourceBundle bundle = FacesContext.getCurrentInstance()
	    .getApplication()
	    .getResourceBundle(FacesContext.getCurrentInstance(), "msgs");

    private String search = "";

    public BeanUsuario() {

    }

    public User getUser() {
	return User;
    }

    public void setUser(User User) {
	this.User = User;
    }

    public String getSearch() {
	return search;
    }

    public void setSearch(String search) {
	this.search = search;
    }

    /**
     * Método encargado de almacenar en la BD el User del managedBean
     * 
     * @param User
     *            El User a almacenar
     * @return A la vista login.xhtml
     */
    public String register(User usuario) {
	try {
	    User.setStatus(UserStatus.ACTIVE);
	    String exito = Factories.services.nuevoUsuario().registrarUsuario(
		    usuario);
	    if (exito.equals("exito"))
		return "register";
	    else {
		addMessage("El User ya existe");
		return "register/fail";
	    }
	} catch (EntityExistsException e) {
	    addMessage("El User ya existe");
	    return "register/fail";
	}
    }

    public String edit(User User) {

	if (User != null) {
	    this.User = User;
	    return "edit";
	} else
	    return "error";

    }

    public String loadUser() {

	HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
		.getExternalContext().getSession(false);
	User user = (User) session.getAttribute("user");
	if (user != null) {
	    this.User = (User) session.getAttribute("user");
	    return "load";
	}
	return "error";

    }

    /**
     * Método encargado de hacer el logout e invalidar la sesión en curso
     * 
     * @return Vuelta a la vista login.xhtml
     */
    public String logout() {

	HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
		.getExternalContext().getSession(false);
	if (session != null) {
	    session.invalidate();

	}
	return "logout";

    }

    /**
     * Método encargado de resetear la BD con los valores incluidos en el
     * enunciado de la práctica. Se puede acceder desde cualquier vista
     * perteneciente al administrador y nos lleva a la vista principal.xhtml
     */
    public void resetDB() {

	// Factories.services.createUsersService().reset();

    }

}