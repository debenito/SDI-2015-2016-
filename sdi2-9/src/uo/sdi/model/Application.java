package uo.sdi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import uo.sdi.infraestructure.Factories;
import uo.sdi.model.types.ApplicationKey;

@Entity
@Table(name = "TAPPLICATIONS")
@IdClass(ApplicationKey.class)
public class Application {
    @Id
    private Long userId;
    @Id
    private Long tripId;

    public Application() {
    }

    public Application(Long userId, Long tripId) {
	this.userId = userId;
	this.tripId = tripId;
	User user = Factories.persistence.newUserDao().findById(userId);
	Trip trip = Factories.persistence.newTripDao().findById(tripId);
	user.getViajesApplications().add(trip);
	trip.getUserApplications().add(user);
    }

    public Long getUserId() {
	return userId;
    }

    public void setUserId(Long userId) {
	this.userId = userId;
    }

    public Long getTripId() {
	return tripId;
    }

    public void setTripId(Long tripId) {
	this.tripId = tripId;
    }

    @Override
    public String toString() {
	return "Application [userId=" + userId + ", tripId=" + tripId + "]";
    }

    public Long[] makeKey() {
	return new Long[] { userId, tripId };
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((tripId == null) ? 0 : tripId.hashCode());
	result = prime * result + ((userId == null) ? 0 : userId.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Application other = (Application) obj;
	if (tripId == null) {
	    if (other.tripId != null)
		return false;
	} else if (!tripId.equals(other.tripId))
	    return false;
	if (userId == null) {
	    if (other.userId != null)
		return false;
	} else if (!userId.equals(other.userId))
	    return false;
	return true;
    }

}
