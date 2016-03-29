package uo.sdi.bussines.impl.classes.application;


public class ApplicacionAlta {
    /*
     * public String apuntarse(Long tripId, Long userId) { Long[] id = { userId,
     * tripId }; if (Factories.persistence.newApplicationDao().findById(id) !=
     * null) return "YaApuntado"; else { Application p = new Application();
     * p.setTripId(tripId); p.setUserId(userId);
     * Factories.persistence.newApplicationDao().save(p); return "Apuntado"; } }
     */
    /*
     * public String confirmarPlaza(Long idViaje, Long idUsuario) { Trip viaje =
     * Factories.persistence.newTripDao().findById(idViaje); if
     * (!exitePasajero(idViaje, idUsuario) && viaje.getAvailablePax() != 0) {
     * viaje.setAvailablePax(viaje.getAvailablePax() - 1);
     * Factories.persistence.newTripDao().update(viaje); Seat asiento =
     * crearAsiento(idUsuario, idViaje); asiento.setStatus(SeatStatus.ACCEPTED);
     * Factories.persistence.newSeatDao().save(asiento); return "siguiente"; }
     * else if (viaje.getAvailablePax() == 0) return "cupo"; else { Seat asiento
     * = Factories.persistence.newSeatDao() .findByUserAndTrip(idUsuario,
     * idViaje); asiento.setStatus(SeatStatus.ACCEPTED);
     * Factories.persistence.newSeatDao().update(asiento);
     * viaje.setAvailablePax(viaje.getAvailablePax() - 1);
     * Factories.persistence.newTripDao().update(viaje); return "siguiente"; } }
     * 
     * private boolean exitePasajero(Long tripId, Long userId) { // TODO
     * Auto-generated method stub return
     * Factories.persistence.newSeatDao().findByUserAndTrip(userId, tripId) !=
     * null; }
     * 
     * private Seat crearAsiento(Long idUsuario, Long idViaje) { Seat asiento =
     * new Seat(); asiento.setComment("");
     * asiento.setStatus(SeatStatus.ACCEPTED); asiento.setTripId(idViaje);
     * asiento.setUserId(idUsuario); return asiento; }
     */
}
