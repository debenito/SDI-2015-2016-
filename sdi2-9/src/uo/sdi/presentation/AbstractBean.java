package uo.sdi.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class AbstractBean {

	public void addMessage(String mensaje) {
		FacesMessage msg = new FacesMessage(mensaje, "ERROR MSG");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
}
