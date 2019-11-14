package com.app.college.mobilecampus.model;

public class Creditos {

    //La primera posicion del arreglo son los creditos realizados y la segunda son los creditos requeridos
    private int [] compuclub;
    private int [] investigacion;
    private int [] bienestar;
    private int [] emprendimiento;
    private int [] academicos;

    public Creditos() {
        compuclub = new int[]{0,0};
        investigacion = new int[]{0,0};
        bienestar = new int[]{0,0};
        emprendimiento = new int[]{0,0};
        academicos = new int[]{0,0};
    }

    public int [] getCompuclub() {
        return this.compuclub;
    }
    public int [] getInvestigacion() {
        return this.investigacion;
    }
    public int [] getBienestar() {
        return this.bienestar;
    }
    public int [] getEmprendimiento() {return this.emprendimiento;}
    public int [] getAcademicos() {
        return academicos;
    }

    public void setCompuclub(int obtenido, int cantidad) {
        compuclub [0] = obtenido;
        compuclub [1] = cantidad;
    }

    public void setInvestigacion(int obtenido, int cantidad) {
        investigacion [0] = obtenido;
        investigacion [1] = cantidad;
    }

    public void setBienestar(int obtenido, int cantidad) {
        bienestar [0] = obtenido;
        bienestar [1] = cantidad;
    }

    public void setEmprendimiento(int obtenido, int cantidad) {
        emprendimiento[0] = obtenido;
        emprendimiento[1] = cantidad;
    }

    public void setAcademicos(int obtenido, int cantidad) {
        academicos[0] = obtenido;
        academicos[1] = cantidad;
    }
}
