package uo.sdi.listener;

import java.io.FileOutputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import alb.util.log.Log;

public class ContactoInicializacion implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		// Guardar el contacto 
		String copyright=(String)arg0.getServletContext().getAttribute("copy");
		String emailContacto=(String)arg0.getServletContext().getAttribute("uo213487");
		String emailContacto2=(String)arg0.getServletContext().getAttribute("uo214459");
		Properties properties = new Properties();
		properties.setProperty("copy", copyright);
		properties.setProperty("uo213487", emailContacto);
		properties.setProperty("uo214459", emailContacto2);
		try {
	        properties.store(new FileOutputStream(arg0.getServletContext().getRealPath(arg0.getServletContext().getInitParameter("ubicacionDelContadorDeSesiones"))),"Lista de propiedades");
	        
		} catch (Exception e) {}
		Log.debug("Persistiendo copyright de sesiones de usuario con el valor: %s",copyright);
		Log.debug("Persistiendo email 1 usuario con el valor: %s",emailContacto);
		Log.debug("Persistiendo email 2 usuario con el valor: %s",emailContacto2);

	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		// Leer el copyright
		String copyright="0";
		String emailContacto="0";
		String emailContacto2="0";
		Properties properties = new Properties();
		try {
	 		properties.load(arg0.getServletContext().getResourceAsStream(arg0.getServletContext().getInitParameter("ubicacionDelContadorDeSesiones")));
			copyright=properties.getProperty("copy");
			emailContacto=properties.getProperty("uo213487");
			emailContacto2=properties.getProperty("uo214459");
		} catch (Exception e) {}
		arg0.getServletContext().setAttribute("copy",copyright);
		arg0.getServletContext().setAttribute("uo213487",emailContacto);
		arg0.getServletContext().setAttribute("uo214459",emailContacto2);
		Log.debug("Inicializando email de contacto 1 a: %s",emailContacto);
		Log.debug("Inicializando email de contacto 2 a: %s",emailContacto2);
		Log.debug("Inicializando copyright de contacto a: %s",copyright);
	}

}