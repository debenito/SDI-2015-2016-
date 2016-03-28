package uo.sdi.presentation.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

@FacesValidator("PasswordsValidator")
public class PasswordValidator extends AbstractValidator {

	@Override
	public void validate(FacesContext arg0, UIComponent component, Object value)
			throws ValidatorException {

		String password = value.toString();

		UIInput uiInputConfirmPassword = (UIInput) component.getAttributes()
				.get("fpasswordRepeat");
		String confirmPassword = uiInputConfirmPassword.getSubmittedValue()
				.toString();

		if (password == null || password.isEmpty()) {
			// addMessage("El campo contraseña no puede estar vacío");
			throw new ValidatorException(new FacesMessage(getBundle()
					.getString("emptyPass")));
		} else {

			if (confirmPassword == null || confirmPassword.isEmpty()) {
				// addMessage("El campo repetir contraseña no pueden estar vacío");
				throw new ValidatorException(new FacesMessage(getBundle()
						.getString("emptyRepeat")));
			}

			else if (!password.equals(confirmPassword)) {

				// addMessage("Las contraseñas deben ser iguales.");
				throw new ValidatorException(new FacesMessage(getBundle()
						.getString("samePass")));
			}
		}
	}
}
