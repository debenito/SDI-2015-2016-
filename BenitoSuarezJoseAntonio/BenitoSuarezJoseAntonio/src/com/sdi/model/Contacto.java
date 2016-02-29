package com.sdi.model;

public class Contacto {

    private Integer id;
    private String nombre;
    private String apellidos;
    private String email;
    private String direccion;
    private String ciudad;
    private String agenda_Usuario;

    public String getDireccion() {
	return direccion;
    }

    public void setDireccion(String direccion) {
	this.direccion = direccion;
    }

    public String getCiudad() {
	return ciudad;
    }

    public void setCiudad(String ciudad) {
	this.ciudad = ciudad;
    }

    public String getAgenda_Usuario() {
	return agenda_Usuario;
    }

    public void setAgenda_Usuario(String agenda_Usuario) {
	this.agenda_Usuario = agenda_Usuario;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getApellidos() {
	return apellidos;
    }

    public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
    }

    public String getEmail() {
	return email;
    }

    @Override
    public String toString() {
	return this.email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getId() {
	return id;
    }

}
