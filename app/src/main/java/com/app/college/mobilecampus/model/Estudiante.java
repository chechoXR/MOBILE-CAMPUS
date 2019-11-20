package com.app.college.mobilecampus.model;

import androidx.annotation.NonNull;

public class Estudiante {

    private String nombre,apellido,correo,usuario,id;

    public Estudiante(String nombre, String apellido, String correo, String usuario, String id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.usuario = usuario;
        this.id = id; //Codigo Estudiante
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", codigo='" + id + '\'' +
                '}';
    }

}
