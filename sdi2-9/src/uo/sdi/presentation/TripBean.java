package uo.sdi.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import uo.sdi.model.Trip;
import uo.sdi.model.types.AddressPoint;
import uo.sdi.model.types.TripStatus;

@ManagedBean(name = "viaje")
@ApplicationScoped
public class TripBean implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private Long id;

    private AddressPoint departure;
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

    public TripBean() {

    }

    public TripBean(Trip trip) {
	setArrivalDate(trip.getArrivalDate());
	setAvailablePax(trip.getAvailablePax());
	setClosingDate(trip.getClosingDate());
	setComments(trip.getComments());
	setDeparture(trip.getDeparture());
	setDepartureDate(trip.getDepartureDate());
	setDestination(trip.getDestination());
	setEstimatedCost(trip.getEstimatedCost());
	setId(trip.getId());
	setMaxPax(trip.getMaxPax());
	setPromoterId(trip.getPromoterId());
	setStatus(trip.getStatus());
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

    public List<TripBean> create(List<Trip> viajes) {
	List<TripBean> lista = new ArrayList<>();
	for (Trip trip : viajes) {
	    lista.add(new TripBean(trip));
	}
	return lista;
    }

}
