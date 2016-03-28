package uo.sdi.presentation;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name = "settings")
@SessionScoped
public class SettingsBean {
	private static final long serialVersionUID = 1231232L;

	private ResourceBundle bundle;

	private Map<String, Object> countries;

	private Locale locale = Locale.US;
	private String localeCode;

	public SettingsBean() {

		bundle = FacesContext.getCurrentInstance().getApplication()
				.getResourceBundle(FacesContext.getCurrentInstance(), "msgs");
		countries = new LinkedHashMap<String, Object>();

		countries.put(bundle.getString("en"), Locale.ENGLISH); // label, value
		countries.put(bundle.getString("es"), new Locale("es"));
		localeCode = FacesContext.getCurrentInstance().getViewRoot()
				.getLocale().getDisplayCountry();

	}

	public Map<String, Object> getCountriesInMap() {
		return countries;
	}

	/**
	 * Se encarga de cambiar el Locale del FacesContext y además volver a
	 * generar tanto las migas de pan como de nuevo una lista actualizada de los
	 * idiomas en el idioma escogido. Se llama desde cualquier vista y no nos
	 * lleva a ninguna vista diferente.
	 * 
	 * @param e
	 */
	public void changeLanguage(ValueChangeEvent e) {
		String menuOption = e.getNewValue().toString();
		// loop country map to compare the locale code
		for (Map.Entry<String, Object> entry : countries.entrySet())
			if (entry.getValue().toString().equals(menuOption)) {
				locale = (Locale) entry.getValue();
				FacesContext.getCurrentInstance().getViewRoot()
						.setLocale(locale);

				bundle = FacesContext
						.getCurrentInstance()
						.getApplication()
						.getResourceBundle(FacesContext.getCurrentInstance(),
								"msgs");
				countries = new LinkedHashMap<String, Object>();
				countries.put(bundle.getString("en"), Locale.ENGLISH); // label,
																		// //
																		// value
				countries.put(bundle.getString("es"), new Locale("es"));

				localeCode = bundle.getString(menuOption);
			//	generateBreadCrumb();
			}
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Map<String, Object> getCountries() {
		return countries;
	}

	public void setCountries(Map<String, Object> countries) {
		this.countries = countries;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * Método encargado de generar las migas de pan a partir de la vista actual.
	 * Recupera el bean BreadCrumb almacenado en sesión y procesa la vista para
	 * regeneraralas.
	 */
/*	public void generateBreadCrumb() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fctx.getExternalContext()
				.getSession(true);

		BreadCrumbBean breadcrumbBean = (BreadCrumbBean) session
				.getAttribute("breadcrumb");

		String viewId = FacesContext.getCurrentInstance().getViewRoot()
				.getViewId();

		if (viewId.contains("/")) {
			int index = viewId.lastIndexOf('/');
			viewId = viewId.substring(index + 1, viewId.length());
			viewId = viewId.replace(".xhtml", "");
			if (breadcrumbBean != null)
				breadcrumbBean.processString(viewId);
		}

	}*/
}
