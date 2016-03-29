package uo.sdi.bussines.impl.classes.application;

import uo.sdi.bussines.Command;
import uo.sdi.infraestructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.persistence.util.Jpa;

public class Apuntarse implements Command {
    private Long userId;
    private Long tripId;

    public Apuntarse(Long tripId, Long userId) {
	this.tripId = tripId;
	this.userId = userId;
    }

    @Override
    public Object execute() {
	// TODO Auto-generated method stub
	Long[] id = { userId, tripId };
	if (Factories.persistence.newApplicationDao().findById(id) != null)
	    return "YaApuntado";
	else {
	    Application p = new Application();
	    p.setTripId(tripId);
	    p.setUserId(userId);
	    Jpa.getManager().persist(p);
	    return "Apuntado";
	}
    }

}
