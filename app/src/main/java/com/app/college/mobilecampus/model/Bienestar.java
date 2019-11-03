package com.app.college.mobilecampus.model;

public class Bienestar {

    private String nombre;
    private String modalidad;
    private String descripcion;
    private String correo;
    private String tipo;
    private String profesor;

    public Bienestar(String nombre, String modalidad, String descripcion, String correo, String tipo, String profesor) {
        this.nombre = nombre;
        this.modalidad = modalidad;
        this.descripcion = descripcion;
        this.correo = correo;
        this.tipo = tipo;
        this.profesor = profesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
}
