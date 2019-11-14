package com.app.college.mobilecampus.todo;

import androidx.annotation.NonNull;

public class Tarea {

    private int id;
    private String nombre;
    private String descripcion;
    private String fecha_inicio;
    private String fecha_fin;
    private double calificacion;
    private int id_materia;
    private int completada;

    public Tarea(int id, String nombre, String descripcion, String fecha_inicio, String fecha_fin, double calificacion, int id_materia, int completada) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.calificacion = calificacion;
        this.id_materia = id_materia;
        this.completada = completada;
    }
    public Tarea(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public int getCompletada() {
        return completada;
    }

    public void setCompletada(int completada) {
        this.completada = completada;
    }

    @NonNull
    @Override
    public String toString() {
        return "ID: "+ id + "   |   " + "Nombre: " + nombre;
    }
}
