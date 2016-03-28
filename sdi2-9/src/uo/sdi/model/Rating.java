package uo.sdi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.crypto.Data;

/**
 * This class is not an entity, it is a DTO with the same fields as a row in the
 * table
 * 
 * @see Data Transfer Object pattern
 * @author alb
 * 
 */
@Entity
@Table(name = "TRATINGS")
public class Rating {
    @Id
    private Long id;

    private Long seatFromTripId;
    private Long seatFromUserId;
    private Long seatAboutTripId;
    private Long seatAboutUserId;

    private String comment;
    private Integer value = 0;

    @OneToMany(mappedBy = "ratingFrom")
    private Set<Seat> seatsFrom = new HashSet<Seat>();
    @OneToMany(mappedBy = "ratingAbout")
    private Set<Seat> seatAbout = new HashSet<Seat>();

    public Rating() {
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getSeatFromTripId() {
	return seatFromTripId;
    }

    public void setSeatFromTripId(Long seatFromTripId) {
	this.seatFromTripId = seatFromTripId;
    }

    public Long getSeatFromUserId() {
	return seatFromUserId;
    }

    public void setSeatFromUserId(Long seatFromUserId) {
	this.seatFromUserId = seatFromUserId;
    }

    public Long getSeatAboutTripId() {
	return seatAboutTripId;
    }

    public void setSeatAboutTripId(Long seatAboutTripId) {
	this.seatAboutTripId = seatAboutTripId;
    }

    public Long getSeatAboutUserId() {
	return seatAboutUserId;
    }

    public void setSeatAboutUserId(Long seatAboutUserId) {
	this.seatAboutUserId = seatAboutUserId;
    }

    public String getComment() {
	return comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

    public Integer getValue() {
	return value;
    }

    public void setValue(Integer value) {
	this.value = value;
    }

    public Set<Seat> getSeatsFrom() {
	return seatsFrom;
    }

    public Set<Seat> getSeatAbout() {
	return seatAbout;
    }

    @Override
    public String toString() {
	return "Rating [id=" + id + ", comment=" + comment + ", value=" + value
		+ ", seatFromTripId=" + seatFromTripId + ", seatFromUserId="
		+ seatFromUserId + ", seatAboutTripId=" + seatAboutTripId
		+ ", seatAboutUserId=" + seatAboutUserId + "]";
    }

}
