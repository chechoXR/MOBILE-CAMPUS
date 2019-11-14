package com.app.college.mobilecampus.todo;

public class Materia {

    private int id;
    private String nombre;
    private String correo_profesor;

    public Materia(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public Materia(){
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

    public String getCorreo_profesor() { return correo_profesor; }

    public void setCorreo_profesor(String correo_profesor) { this.correo_profesor = correo_profesor; }

}
