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
		String copyright=(String)arg0.getServletContext().getAttribute("copyright");
		String emailContacto=(String)arg0.getServletContext().getAttribute("emailContacto");
		Properties properties = new Properties();
		properties.setProperty("copyright", copyright);
		properties.setProperty("emailContacto", emailContacto);
		try {
	        properties.store(new FileOutputStream(arg0.getServletContext().getRealPath(arg0.getServletContext().getInitParameter("ubicacionDelContadorDeSesiones"))),"Lista de propiedades");
	        
		} catch (Exception e) {}
		Log.debug("Persistiendo copyright de sesiones de usuario con el valor: %s",copyright);
		Log.debug("Persistiendo contador de sesiones de usuario con el valor: %s",emailContacto);
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		// Leer el copyright
		String copyright="0";
		String emailContacto="0";
		Properties properties = new Properties();
		try {
			properties.load(arg0.getServletContext().getResourceAsStream(arg0.getServletContext().getInitParameter("ubicacionDelContadorDeSesiones")));
			copyright=properties.getProperty("copyright");
			emailContacto=properties.getProperty("emailContacto");
		} catch (Exception e) {}
		arg0.getServletContext().setAttribute("copyright",copyright);
		arg0.getServletContext().setAttribute("emailContacto",emailContacto);
		Log.debug("Inicializando email de contacto a: %s",emailContacto);
		Log.debug("Inicializando copyright de contacto a: %s",copyright);
	}

}