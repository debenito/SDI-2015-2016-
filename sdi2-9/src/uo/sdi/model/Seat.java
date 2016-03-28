package uo.sdi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.crypto.Data;

import uo.sdi.model.types.SeatKey;
import uo.sdi.model.types.SeatStatus;

/**
 * This class is not an entity, it is a DTO with the same fields as a row in the
 * table
 * 
 * @see Data Transfer Object pattern
 * @author alb
 * 
 */
@Entity
@Table(name = "TSEATS")
@IdClass(SeatKey.class)
public class Seat {
    @Id
    private Long userId;
    @Id
    private Long tripId;

    private String comment;
    private SeatStatus status;
    @ManyToOne
    private Rating ratingFrom;
    @ManyToOne
    private Rating ratingAbout;

    public Seat() {
    }

    public Long[] makeKey() {
	return new Long[] { userId, tripId };
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

    public String getComment() {
	return comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

    public SeatStatus getStatus() {
	return status;
    }

    public void setStatus(SeatStatus status) {
	this.status = status;
    }

    public void setRatingFrom(Rating ratingFrom) {
	this.ratingFrom = ratingFrom;
    }

    public void setRatingAbout(Rating ratingAbout) {
	this.ratingAbout = ratingAbout;
    }

    @Override
    public String toString() {
	return "Seat [userId=" + userId + ", tripId=" + tripId + ", comment="
		+ comment + ", status=" + status + "]";
    }

}
