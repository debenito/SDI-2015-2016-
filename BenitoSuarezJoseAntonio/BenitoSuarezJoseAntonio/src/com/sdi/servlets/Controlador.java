package com.sdi.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;

import com.sdi.acciones.Accion;
import com.sdi.acciones.AltaContactosAction;
import com.sdi.acciones.AltaContactosUsuarioAction;
import com.sdi.acciones.AnadirUsuariosAction;
import com.sdi.acciones.EditarCorreoAction;
import com.sdi.acciones.ErrorAction;
import com.sdi.acciones.ListaCorreosEntradaAction;
import com.sdi.acciones.ListaCorreosPapeleraAction;
import com.sdi.acciones.ListaCorreosSalidaAction;
import com.sdi.acciones.ListarContactosAction;
import com.sdi.acciones.ListarContactosUsuarioAction;
import com.sdi.acciones.ListarUsuariosAction;
import com.sdi.acciones.LogoutAction;
import com.sdi.acciones.ModificarDatosAction;
import com.sdi.acciones.ModificarDatosUsuariosAction;
import com.sdi.acciones.NuevoCorreoAction;
import com.sdi.acciones.RegistrarseAction;
import com.sdi.acciones.ValidarseAction;
import com.sdi.model.Usuario;

public class Controlador extends javax.servlet.http.HttpServlet {

    private static final long serialVersionUID = 1L;
    private Map<String, Map<String, Accion>> mapaDeAcciones; // <rol, <opcion,
							     // objeto
							     // Accion>>
    private Map<String, Map<String, Map<String, String>>> mapaDeNavegacion;

    // <rol <opcion <resultado, JSP>>>

    public void init() throws ServletException {
	crearMapaAcciones();
	crearMapaDeJSP();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
	    throws IOException, ServletException {

	String opcion, resultado, jspSiguiente;
	Accion accion;
	String rolAntes, rolDespues;

	try {
	    opcion = req.getServletPath().replace("/", "");

	    rolAntes = obtenerRolDeSesion(req);

	    accion = buscarAccionParaOpcion(rolAntes, opcion);

	    resultado = accion.execute(req, res);

	    rolDespues = obtenerRolDeSesion(req);

	    jspSiguiente = buscarJSPSegun(rolDespues, opcion, resultado);

	} catch (Exception e) {

	    req.getSession().invalidate();

	    Log.error("Se ha producido alguna excepción no manejada [%s]", e);

	    jspSiguiente = "/login.jsp";
	}

	RequestDispatcher dispatcher = getServletContext()
		.getRequestDispatcher(jspSiguiente);

	dispatcher.forward(req, res);
    }

    private String obtenerRolDeSesion(HttpServletRequest req) {
	HttpSession sesion = req.getSession();
	if (sesion.getAttribute("user") == null)
	    return "PUBLICO";
	else if (((Usuario) sesion.getAttribute("user")).getRol().equals(
		"Administrador"))
	    return "ADMIN";
	else
	    return "REGISTRADO";
    }

    // Obtiene un objeto accion en funci�n de la opci�n
    // enviada desde el navegador
    private Accion buscarAccionParaOpcion(String rol, String opcion) {

	Accion accion = mapaDeAcciones.get(rol).get(opcion);
	Log.debug("Elegida acción [%s] para opción [%s] y rol [%s]", accion,
		opcion, rol);
	return accion;
    }

    // Obtiene la p�gina JSP a la que habr� que entregar el
    // control el funci�n de la opci�n enviada desde el navegador
    // y el resultado de la ejecuci�n de la acci�n asociada
    private String buscarJSPSegun(String rol, String opcion, String resultado) {

	String jspSiguiente = mapaDeNavegacion.get(rol).get(opcion)
		.get(resultado);
	Log.debug("Elegida página siguiente [%s] para el resultado [%s] "
		+ "tras realizar [%s] con rol [%s]", jspSiguiente, resultado,
		opcion, rol);
	return jspSiguiente;
    }

    private void crearMapaAcciones() {
	mapaDeAcciones = new HashMap<String, Map<String, Accion>>();

	Map<String, Accion> mapaPublico = new HashMap<String, Accion>();
	mapaPublico.put("validarse", new ValidarseAction());
	mapaPublico.put("registrarDatos", new RegistrarseAction());
	mapaPublico.put("logout", new LogoutAction());
	mapaPublico.put("error", new ErrorAction());
	mapaDeAcciones.put("PUBLICO", mapaPublico);

	Map<String, Accion> mapaRegistrado = new HashMap<String, Accion>();
	mapaRegistrado.put("modificarDatos", new ModificarDatosAction());
	mapaRegistrado.put("logout", new LogoutAction());
	mapaRegistrado.put("error", new ErrorAction());
	mapaRegistrado.put("listarContactos",
		new ListarContactosUsuarioAction());
	mapaRegistrado.put("altaContacto", new AltaContactosUsuarioAction());
	mapaRegistrado.put("listaEntrada", new ListaCorreosEntradaAction());
	mapaRegistrado.put("listaSalida", new ListaCorreosSalidaAction());
	mapaRegistrado.put("papelera", new ListaCorreosPapeleraAction());
	mapaRegistrado.put("anadir", new AnadirUsuariosAction());
	mapaRegistrado.put("nuevoCorreo", new NuevoCorreoAction());
	mapaRegistrado.put("guardar", new EditarCorreoAction());

	mapaDeAcciones.put("REGISTRADO", mapaRegistrado);

	Map<String, Accion> mapaAdmin = new HashMap<String, Accion>();
	mapaAdmin.put("listarUsuarios", new ListarUsuariosAction());
	mapaAdmin.put("logout", new LogoutAction());
	mapaAdmin.put("error", new ErrorAction());
	mapaAdmin.put("cambiarDatos", new ModificarDatosUsuariosAction());
	mapaAdmin.put("listarContactos", new ListarContactosAction());
	mapaAdmin.put("altaContacto", new AltaContactosAction());
	mapaDeAcciones.put("ADMIN", mapaAdmin);

    }

    private void crearMapaDeJSP() {

	mapaDeNavegacion = new HashMap<String, Map<String, Map<String, String>>>();

	Map<String, Map<String, String>> opcionResJSP = new HashMap<String, Map<String, String>>();
	Map<String, String> resJSP = new HashMap<String, String>();

	// Mapa de navegación del público
	resJSP.put("FRACASO", "/error.jsp");
	opcionResJSP.put("validarse", resJSP);
	resJSP = new HashMap<String, String>();
	resJSP.put("EXITO", "/login.jsp");
	resJSP.put("FRACASO", "/error.jsp");
	opcionResJSP.put("registrarDatos", resJSP);
	resJSP = new HashMap<String, String>();
	resJSP.put("FRACASO", "/error.jsp");
	resJSP.put("EXITO", "/login.jsp");
	opcionResJSP.put("logout", resJSP);
	mapaDeNavegacion.put("PUBLICO", opcionResJSP);

	opcionResJSP = new HashMap<String, Map<String, String>>();
	resJSP = new HashMap<String, String>();

	// Mapa de navegación de usuarios registrados
	resJSP.put("EXITO", "/principal.jsp");
	resJSP.put("FRACASO", "/error.jsp");
	opcionResJSP.put("validarse", resJSP);
	resJSP = new HashMap<String, String>();
	resJSP.put("FRACASO", "/error.jsp");
	resJSP.put("EXITO", "/principal.jsp");
	opcionResJSP.put("modificarDatos", resJSP);
	resJSP = new HashMap<String, String>();
	resJSP.put("FRACASO", "/error.jsp");
	resJSP.put("EXITO", "/login.jsp");
	opcionResJSP.put("logout", resJSP);
	resJSP = new HashMap<String, String>();
	resJSP.put("FRACASO", "/error.jsp");
	resJSP.put("EXITO", "/listacontactos.jsp");
	opcionResJSP.put("listarContactos", resJSP);
	resJSP = new HashMap<String, String>();
	resJSP.put("FRACASO", "/error.jsp");
	resJSP.put("EXITO", "/principal.jsp");
	opcionResJSP.put("altaContacto", resJSP);
	resJSP = new HashMap<String, String>();
	resJSP.put("FRACASO", "/error.jsp");
	resJSP.put("EXITO", "/bandejaentrada.jsp");
	opcionResJSP.put("listaEntrada", resJSP);
	resJSP = new HashMap<String, String>();
	resJSP.put("FRACASO", "/error.jsp");
	resJSP.put("EXITO", "/bandejasalida.jsp");
	opcionResJSP.put("listaSalida", resJSP);
	resJSP = new HashMap<String, String>();
	resJSP.put("FRACASO", "/error.jsp");
	resJSP.put("EXITO", "/papelera.jsp");
	opcionResJSP.put("papelera", resJSP);
	resJSP = new HashMap<String, String>();
	resJSP.put("FRACASO", "/error.jsp");
	resJSP.put("EXITO", "/listacontactos.jsp");
	opcionResJSP.put("anadir", resJSP);
	resJSP = new HashMap<String, String>();
	resJSP.put("FRACASO", "/error.jsp");
	resJSP.put("EXITO", "/principal.jsp");
	opcionResJSP.put("nuevoCorreo", resJSP);
	resJSP = new HashMap<String, String>();
	resJSP.put("FRACASO", "/error.jsp");
	resJSP.put("EXITO", "/bandejasalida.jsp");
	opcionResJSP.put("guardar", resJSP);
	mapaDeNavegacion.put("REGISTRADO", opcionResJSP);

	opcionResJSP = new HashMap<String, Map<String, String>>();
	resJSP = new HashMap<String, String>();

	// Mapa de navegación del administrador
	resJSP.put("FRACASO", "/error.jsp");
	resJSP.put("EXITO", "/adminprincipal.jsp");
	opcionResJSP.put("validarse", resJSP);
	resJSP = new HashMap<String, String>();
	resJSP.put("FRACASO", "/error.jsp");
	resJSP.put("EXITO", "/listausuarios.jsp");
	opcionResJSP.put("listarUsuarios", resJSP);
	resJSP = new HashMap<String, String>();
	resJSP.put("FRACASO", "/error.jsp");
	resJSP.put("EXITO", "/login.jsp");
	opcionResJSP.put("logout", resJSP);
	resJSP = new HashMap<String, String>();
	resJSP.put("FRACASO", "/error.jsp");
	resJSP.put("EXITO", "/adminprincipal.jsp");
	opcionResJSP.put("cambiarDatos", resJSP);
	resJSP = new HashMap<String, String>();
	resJSP.put("FRACASO", "/error.jsp");
	resJSP.put("EXITO", "/listacontactos.jsp");
	opcionResJSP.put("listarContactos", resJSP);
	resJSP = new HashMap<String, String>();
	resJSP.put("FRACASO", "/error.jsp");
	resJSP.put("EXITO", "/adminprincipal.jsp");
	opcionResJSP.put("altaContacto", resJSP);
	mapaDeNavegacion.put("ADMIN", opcionResJSP);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
	    throws IOException, ServletException {

	doGet(req, res);
    }

}