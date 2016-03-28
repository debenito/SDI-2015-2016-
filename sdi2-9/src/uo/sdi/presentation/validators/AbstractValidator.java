package uo.sdi.presentation.validators;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;

public abstract class AbstractValidator implements Validator {
	public ResourceBundle getBundle() {
		ResourceBundle bundle = FacesContext.getCurrentInstance()
				.getApplication()
				.getResourceBundle(FacesContext.getCurrentInstance(), "msgs");
		return bundle;

	}
}
