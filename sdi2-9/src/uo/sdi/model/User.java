package uo.sdi.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import uo.sdi.model.types.UserStatus;

@Entity
@Table(name = "TUSERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;

    private UserStatus status;

    @OneToMany(mappedBy = "user")
    private Set<Trip> viajes = new HashSet<Trip>();
    @ManyToMany(mappedBy = "userApplications")
    private Set<Trip> viajesApplications = new HashSet<Trip>();
    @ManyToMany(mappedBy = "trippers")
    private Set<Trip> viajesViajeros = new HashSet<Trip>();

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public UserStatus getStatus() {
	return status;
    }

    public void setStatus(UserStatus status) {
	this.status = status;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getLogin() {
	return login;
    }

    public void setLogin(String login) {
	this.login = login;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getSurname() {
	return surname;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    public Set<Trip> _getViajes() {
	return viajes;
    }

    public Set<Trip> getViajesViajeros() {
	return viajesViajeros;
    }

    public Set<Trip> getViajesApplications() {
	return viajesApplications;
    }

    public Set<Trip> getViajes() {
	return Collections.unmodifiableSet(viajes);
    }

    @Override
    public String toString() {
	return "User [id=" + id + ", login=" + login + ", password=" + password
		+ ", name=" + name + ", surname=" + surname + ", status="
		+ status + ", email=" + email + "]";
    }

}
