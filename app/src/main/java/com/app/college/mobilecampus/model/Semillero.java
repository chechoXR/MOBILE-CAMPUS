package com.app.college.mobilecampus.model;

public class Semillero {

    private String nombre;
    private String descripcion;
    private String condicion;
    private String profesor;
    private String correo_profesor;
    private String estado;

    public Semillero(String nombre, String descripcion, String condicion, String profesor, String correo_profesor, String estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.condicion = condicion;
        this.profesor = profesor;
        this.correo_profesor = correo_profesor;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getCorreo_profesor() {
        return correo_profesor;
    }

    public void setCorreo_profesor(String correo_profesor) {
        this.correo_profesor = correo_profesor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Semillero{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", condicion='" + condicion + '\'' +
                ", profesor='" + profesor + '\'' +
                ", correo_profesor='" + correo_profesor + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
