package uo.sdi.model.types;

import java.io.Serializable;

public class SeatKey implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    Long userId;

    Long tripId;

    public SeatKey() {

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
	SeatKey other = (SeatKey) obj;
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
