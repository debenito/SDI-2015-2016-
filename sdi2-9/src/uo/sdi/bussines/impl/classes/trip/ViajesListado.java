package uo.sdi.bussines.impl.classes.trip;

import java.util.Date;
import java.util.List;

import uo.sdi.infraestructure.Factories;
import uo.sdi.model.Trip;

public class ViajesListado {
	
	  public List<Trip> listarViajesActivos(Date hoy) {
		  return Factories.persistence.newTripDao().listarViajesActivos(hoy);
	  }
	  public List<Trip> listarViajesActivosPromotor(Date hoy, Long id){
		  return Factories.persistence.newTripDao().listarViajesActivosPromotor(hoy, id);
	  }
	    public List<Trip> listaViajesParticipacionPromotor(Long id) {
		// TODO Auto-generated method stub
		return Factories.persistence.newTripDao().listaViajesParticipacionPromotor(id);
	    }
	 
	    public List<Trip> listaViajesParticipacionPendientes(Long id) {
		// TODO Auto-generated method stub
		return Factories.persistence.newTripDao().listaViajesParticipacionPendientes(id);
	    }

	    
	    public List<Trip> listaViajesParticipacionSinPlaza(Long id) {
		// TODO Auto-generated method stub
		return Factories.persistence.newTripDao().listaViajesParticipacionSinPlaza(id);
	    }

	    
	    public List<Trip> listaViajesParticipacionAdmitido(Long id) {
		// TODO Auto-generated method stub
		return Factories.persistence.newTripDao().listaViajesParticipacionAdmitido(id);
	    }

	    
	    public List<Trip> listaViajesParticipacionExcluido(Long id) {
		// TODO Auto-generated method stub
		return Factories.persistence.newTripDao().listaViajesParticipacionExcluido(id);
	    }
		public List<Trip> listaViajesPromotor(Long id) {
			// TODO Auto-generated method stub
			return Factories.persistence.newTripDao().listarViajesPromotor(id);
		}
}
