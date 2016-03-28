package uo.sdi.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.crypto.Data;

import uo.sdi.model.types.AddressPoint;
import uo.sdi.model.types.TripStatus;

/**
 * This class is not an entity, it is a DTO with the same fields as a row in the
 * table
 * 
 * @see Data Transfer Object pattern
 * @author alb
 * 
 */
@Entity
@Table(name = "TTRIPS")
public class Trip {
    @Id
    private Long id;
    @Embedded
    @AttributeOverrides({
	    @AttributeOverride(name = "address", column = @Column(name = "DEPARTURE_ADDRESS")),
	    @AttributeOverride(name = "city", column = @Column(name = "DEPARTURE_CITY")),
	    @AttributeOverride(name = "country", column = @Column(name = "DEPARTURE_COUNTRY")),
	    @AttributeOverride(name = "state", column = @Column(name = "DEPARTURE_STATE")),
	    @AttributeOverride(name = "zipCode", column = @Column(name = "DEPARTURE_ZIPCODE")),
	    @AttributeOverride(name = "waypoint.lat", column = @Column(name = "DEPARTURE_LAT")),
	    @AttributeOverride(name = "waypoint.lon", column = @Column(name = "DEPARTURE_LON")) })
    private AddressPoint departure;
    @Embedded
    @AttributeOverrides({
	    @AttributeOverride(name = "address", column = @Column(name = "DESTINATION_ADDRESS")),
	    @AttributeOverride(name = "city", column = @Column(name = "DESTINATION_CITY")),
	    @AttributeOverride(name = "country", column = @Column(name = "DESTINATION_COUNTRY")),
	    @AttributeOverride(name = "state", column = @Column(name = "DESTINATION_STATE")),
	    @AttributeOverride(name = "zipCode", column = @Column(name = "DESTINATION_ZIPCODE")),
	    @AttributeOverride(name = "waypoint.lat", column = @Column(name = "DESTINATION_LAT")),
	    @AttributeOverride(name = "waypoint.lon", column = @Column(name = "DESTINATION_LON")) })
    private AddressPoint destination;
    private Date arrivalDate;
    private Date departureDate;
    private Date closingDate;
    private Integer availablePax = 0;
    private Integer maxPax = 0;
    private Double estimatedCost = 0.0;
    private String comments;
    private TripStatus status;

    private Long promoterId;

    @ManyToOne
    private User user;
    @ManyToMany
    private Set<User> userApplications = new HashSet<User>();
    @ManyToMany
    private Set<User> trippers = new HashSet<User>();

    public Trip() {
    }

    public AddressPoint getDeparture() {
	return departure;
    }

    public void setDeparture(AddressPoint departure) {
	this.departure = departure;
    }

    public TripStatus getStatus() {
	return status;
    }

    public void setStatus(TripStatus status) {
	this.status = status;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public AddressPoint getDestination() {
	return destination;
    }

    public void setDestination(AddressPoint destination) {
	this.destination = destination;
    }

    public Date getArrivalDate() {
	return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
	this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
	return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
	this.departureDate = departureDate;
    }

    public Date getClosingDate() {
	return closingDate;
    }

    public void setClosingDate(Date closingDate) {
	this.closingDate = closingDate;
    }

    public Integer getAvailablePax() {
	return availablePax;
    }

    public void setAvailablePax(Integer availablePax) {
	this.availablePax = availablePax;
    }

    public Integer getMaxPax() {
	return maxPax;
    }

    public void setMaxPax(Integer maxPax) {
	this.maxPax = maxPax;
    }

    public Double getEstimatedCost() {
	return estimatedCost;
    }

    public void setEstimatedCost(Double estimatedCost) {
	this.estimatedCost = estimatedCost;
    }

    public String getComments() {
	return comments;
    }

    public void setComments(String comments) {
	this.comments = comments;
    }

    public Long getPromoterId() {
	return promoterId;
    }

    public void setPromoterId(Long promoterId) {
	this.promoterId = promoterId;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Set<User> getTrippers() {
	return trippers;
    }

    public Set<User> getUserApplications() {
	return userApplications;
    }

    @Override
    public String toString() {
	return "Trip [id=" + id + ", departure=" + departure + ", destination="
		+ destination + ", arrivalDate=" + arrivalDate
		+ ", departureDate=" + departureDate + ", closingDate="
		+ closingDate + ", availablePax=" + availablePax + ", maxPax="
		+ maxPax + ", estimatedCost=" + estimatedCost + ", comments="
		+ comments + ", status=" + status + ", promoterId="
		+ promoterId + "]";
    }

}
