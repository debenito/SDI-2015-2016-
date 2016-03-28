package uo.sdi.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import uo.sdi.infraestructure.Factories;
import uo.sdi.model.Trip;

@ManagedBean(name = "viajes")
@ViewScoped
public class TripsBean extends AbstractBean implements Serializable {
	@ManagedProperty("#{viaje}")
	private TripBean viaje = new TripBean();
	/**
	 * 
	 */
	private static final long serialVersionUID = -6054438861573261945L;
	private List<TripBean> viajesActivos = new ArrayList<TripBean>();
	private List<TripBean> filterBy = new ArrayList<TripBean>();
	private int numRow;

	public List<TripBean> getViajesActivos() {
		return viajesActivos;
	}

	public void setViajesActivos(List<TripBean> viajesActivos) {
		this.viajesActivos = viajesActivos;
	}
	
	public int getNumRow() {
		return numRow;
	}
	
	public void setNumRow(int numRow) {
		this.numRow = numRow;
	}

	@PostConstruct
	public void init() {
		iniciaListaPublica();
		numRow=15;
	}

	public void setService(TripBean viaje) {
		this.viaje = viaje;
	}

	/**
	 * Método encargado de iniciar la lista de contactos pública
	 */
	public void iniciaListaPublica() {

		try {
			List<Trip> viajes = (List<Trip>) Factories.services.nuevoViaje()
					.listarViajesActivos(null);
			viajesActivos = viaje.create(viajes);
			filterBy = viajesActivos;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<TripBean> getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(List<TripBean> filterBy) {
		this.filterBy = filterBy;
	}

}
