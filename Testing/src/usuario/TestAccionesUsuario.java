package usuario;

import java.util.*;

import org.junit.*;

import uo.sdi.acciones.LogicaFactory;
import uo.sdi.acciones.logic.ViajeLogica;
import uo.sdi.model.*;
import uo.sdi.persistence.PersistenceFactory;
import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class TestAccionesUsuario {

	@Before
	public void prepare() {
		setBaseUrl("http://localhost:8280/sesion4.MVCCasero");
	
	}

	/**
	 * Test para inicio de sesion con contraseña incorrecta
	 */
	@Test
	public void testIniciarSesionSinExitoContraseña() {
		// Rellenando el formulario HTML
		beginAt("/"); // Navegar a la URL
		setTextField("nombreUsuario", "user1"); // Rellenar primer campo de
												// formulario
		setTextField("pass", "user2");
		submit(); // Enviar formulario
		assertTitleEquals("ShareMyTrip - Inicie sesión"); // Comprobar título de
															// la página
	}

	/**
	 * Test para inicio de sesion con usuario que no existe
	 */
	@Test
	public void testIniciarSesionSinExitoNombreUsuario() {
		// Rellenando el formulario HTML
		beginAt("/"); // Navegar a la URL
		setTextField("nombreUsuario", "Jose Luis"); // Rellenar primer campo de
													// formulario
		setTextField("pass", "user2");
		submit(); // Enviar formulario
		assertTitleEquals("ShareMyTrip - Inicie sesión"); // Comprobar título de
															// la página
	}

	/**
	 * Test para inicio de sesion correcto
	 */
	@Test
	public void testIniciarSesionConExito() {
		// Rellenando el formulario HTML
		beginAt("/"); // Navegar a la URL
		setTextField("nombreUsuario", "user1"); // Rellenar primer campo de
												// formulario
		setTextField("pass", "user1");
		submit(); // Enviar formulario
		assertTitleEquals("ShareMyTrip - Página principal del usuario"); // Comprobar
																			// título
																			// de
																			// la
																			// página
		assertTextInElement("login", "user1"); // Comprobar cierto elemento
												// contiene cierto texto
		assertTextInElement("name", "Fernando"); // Comprobar cierto elemento
													// contiene cierto texto
		assertTextPresent("Es Vd el usuario número:"); // Comprobar cierto texto
														// está presente
	}

	@Test
	public void testRegistroUsuarioSinExito() {

		beginAt("/registro");
		setTextField("login", "user25");
		setTextField("nombre", "user");
		setTextField("apellidos", "25");
		setTextField("email", "25@mail.com");
		setTextField("pass1", "pass1");
		setTextField("pass2", "pass2");
		submit();
		assertTitleEquals("ShareMyTrip - Registro de nuevos usuarios");
		assertTextPresent("Contraseñas diferentes");

		User user = PersistenceFactory.newUserDao().findByLogin("user25");
		Assert.assertNull(user);
	}

	@Test
	public void testRegistroUsuarioConExito() {

		beginAt("/registro");
		setTextField("login", "user16");
		setTextField("nombre", "user");
		setTextField("apellidos", "16");
		setTextField("email", "16@mail.com");
		setTextField("pass1", "pass1");
		setTextField("pass2", "pass1");
		submit();
		assertTitleEquals("ShareMyTrip - Registro de nuevos usuarios");
		assertTextPresent("Exito al introducir el usuario con login");

		User user = PersistenceFactory.newUserDao().findByLogin("user16");
		Assert.assertNotNull(user);
		PersistenceFactory.newUserDao().delete(user.getId());
	}

	@Test
	public void listarPublico() {
		beginAt("/");
		assertLinkPresent("listarViajes");
		clickLink("listarViajes");
		assertTitleEquals("ShareMyTrip - Listado de viajes");
		List<Trip> viajes = PersistenceFactory.newTripDao()
				.listarViajesActivos(new Date());
		// la cuenta de las filas de la tabla empieza en 1 en vez de en 0, se
		// debe sumar 1 al total de la lista
		assertTableRowCountEquals("viajes", viajes.size() + 1);
	}

	@Test
	public void listarRegistrado() {
		String login="user1";
		String pass="user1";
		ViajeLogica logicaViajes = LogicaFactory.nuevoViaje();
		User user = PersistenceFactory.newUserDao().findByLogin(login);
		
		validar(login, pass);
		
		assertLinkPresent("listarViajes");
		clickLink("listarViajes");
		
		assertTitleEquals("ShareMyTrip - Listado de viajes");
		
		List<Trip> viajes = logicaViajes.listarViajesActivos(user);
		
		assertTableRowCountEquals("viajes", viajes.size() + 1);
	}
	
	@Test
	public void modificarUsuario(){
		String login="user1";
		String pass="user1";
		
		User user= PersistenceFactory.newUserDao().findByLogin(login);
		validar(login, pass);
		
		assertLinkPresent("volver");
		clickLink("volver");
		
		assertTextInElement("login", user.getLogin());  
        assertTextInElement("name", user.getName());
        assertTextInElement("surname", user.getSurname());
        assertTextInElement("email", user.getEmail());
        
        setTextField("pass", "fer");
        setTextField("pass1", "nando");
        setTextField("passVieja", pass);
        clickButton("confirmar");
        
        assertTextPresent("Contraseña nuevas no coinciden");

        setTextField("pass", "fer");
        setTextField("pass1", "fer");
        setTextField("passVieja", "fer");
        clickButton("confirmar");
        
        assertTextPresent("Contraseña vieja no coincide o alguna de ellas esta vacia");
        
        String nuevoLogin="fer";
        setTextField("login",nuevoLogin );
        setTextField("pass", "fer");
        setTextField("pass1", "fer");
        setTextField("passVieja", pass);
        clickButton("confirmar");
        
        User userCambio=PersistenceFactory.newUserDao().findByLogin(nuevoLogin);
        Assert.assertEquals("fer", userCambio.getPassword());
        //reseteamos cambios en BBDD
        userCambio.setLogin(login);
        userCambio.setPassword(pass);
        PersistenceFactory.newUserDao().update(userCambio);
	}
	
	/*
	@Test
	public void verMisViajes(){
		String login="user1";
		String pass="user1";
		 ViajeLogica logicaViajes = LogicaFactory.nuevoViaje();
				 
		User user= PersistenceFactory.newUserDao().findByLogin(login);
		validar(login, pass);
		
		assertLinkPresent("viajesPromotor");
		clickLink("viajesPromotor");
		
		List<Trip> viajes= logicaViajes.listarViajesPromotor(user.getId());
		
		assertTableRowCountEquals("tablaViajes", viajes.size()+1);
		
	}
	*/
	
	@Test
	public void registrarViaje(){
		String login="user1";
		String pass="user1";
		
		validar(login, pass);
		
		assertLinkPresent("viajesPromotor");
		clickLink("viajesPromotor");
		
		
		setTextField("departurecity", "Florencia");
		setTextField("departurestate", "Florencia");
		setTextField("departurecountry", "Florencia");
		setTextField("departureaddress", "Florencia");
		setTextField("departurezipCode", "Florencia");
		setTextField("arrivalDatefecha", "2016-9-21");
		setTextField("arrivalDatehora", "15:00:00");
		setTextField("destinationcity", "Florencia");
		setTextField("destinationstate", "Florencia");
		setTextField("destinationcountry", "Florencia");
		setTextField("destinationaddress", "Florencia");
		setTextField("destinationzipCode", "Florencia");
		setTextField("departureDatefecha", "2016-10-21");
		setTextField("departureDatehora", "12:00:00");
		setTextField("estimatedCost", "5");
		setTextField("comment", "Florencia");
		setTextField("maxPax", "5");
		setTextField("availablePax", "5");
	//	setTextField("closingDatefecha", "2016-10-21");
		setTextField("closingDateHora", "00:00:00");
		
		clickButton("registrar");
		 assertTextPresent("Ajústese al formato slicitado ");
		setTextField("departurecity", "Florencia");
		setTextField("departurestate", "Florencia");
		setTextField("departurecountry", "Florencia");
		setTextField("departureaddress", "Florencia");
		setTextField("departurezipCode", "Florencia");
		setTextField("arrivalDatefecha", "2016-10-21");
		setTextField("arrivalDatehora", "15:00:00");
		setTextField("destinationcity", "Florencia");
		setTextField("destinationstate", "Florencia");
		setTextField("destinationcountry", "Florencia");
		setTextField("destinationaddress", "Florencia");
		setTextField("destinationzipCode", "Florencia");
		setTextField("departureDatefecha", "2016-10-21");
		setTextField("departureDatehora", "12:00:00");
		setTextField("estimatedCost", "5");
		setTextField("comment", "Florencia");
		setTextField("maxPax", "5");
		setTextField("availablePax", "5");
		setTextField("closingDatefecha", "2016-10-21");
		setTextField("closingDateHora", "00:00:00");
		
		setTextField("departurecity", "Florencia");
		setTextField("departurestate", "Florencia");
		setTextField("departurecountry", "Florencia");
		setTextField("departureaddress", "Florencia");
		setTextField("departurezipCode", "Florencia");
		setTextField("arrivalDatefecha", "2016-10-21");
		setTextField("arrivalDatehora", "15:00:00");
		setTextField("destinationcity", "Florencia");
		setTextField("destinationstate", "Florencia");
		setTextField("destinationcountry", "Florencia");
		setTextField("destinationaddress", "Florencia");
		setTextField("destinationzipCode", "Florencia");
		setTextField("departureDatefecha", "2016-10-21");
		setTextField("departureDatehora", "12:00:00");
		setTextField("estimatedCost", "5");
		setTextField("comment", "Florencia");
		setTextField("maxPax", "5");
		setTextField("availablePax", "5");
		setTextField("closingDateFecha", "2016-10-21");
		setTextField("closingDateHora", "00:00:00");
		
		setTextField("departurecity", "Florencia");
		setTextField("departurestate", "Florencia");
		setTextField("departurecountry", "Florencia");
		setTextField("departureaddress", "Florencia");
		setTextField("departurezipCode", "Florencia");
		setTextField("arrivalDatefecha", "2016-10-21");
		setTextField("arrivalDatehora", "15:00:00");
		setTextField("destinationcity", "Florencia");
		setTextField("destinationstate", "Florencia");
		setTextField("destinationcountry", "Florencia");
		setTextField("destinationaddress", "Florencia");
		setTextField("destinationzipCode", "Florencia");
		setTextField("departureDatefecha", "2016-10-21");
		setTextField("departureDatehora", "12:00:00");
		setTextField("estimatedCost", "5");
		setTextField("comment", "Florencia");
		setTextField("maxPax", "5");
		setTextField("availablePax", "5");
		setTextField("closingDateFecha", "2016-10-21");
		setTextField("closingDateHora", "00:00:00");
		
		
		
		
	}
	
	private void validar(String nombre, String pass){
		beginAt("/");
		setTextField("nombreUsuario", nombre); 
		setTextField("pass", pass);
		submit();
	}
	
	

}
