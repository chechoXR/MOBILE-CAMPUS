package com.app.college.mobilecampus.model;

public class Creditos {

    //la primera posicion del arreglo son los creditos realizados y la segunda son los creditos requeridos
    private String [] compuclub;
    private String [] investigacion;
    private String [] bienestar;
    private String [] emprendimiento;

    public Creditos(String[] compuclub, String[] investigacion, String[] bienestar, String[] emprendimiento) {
        this.compuclub = compuclub;
        this.investigacion = investigacion;
        this.bienestar = bienestar;
        this.emprendimiento = emprendimiento;
    }

    public String [] getCompuclub() {
        return compuclub;
    }

    public String [] getInvestigacion() {
        return investigacion;
    }

    public String [] getBienestar() {
        return bienestar;
    }

    public String [] getEmprendimiento() {
        return emprendimiento;
    }

    public void setCompuclub(String[] compuclub) {
        this.compuclub = compuclub;
    }

    public void setInvestigacion(String[] investigacion) {
        this.investigacion = investigacion;
    }

    public void setBienestar(String[] bienestar) {
        this.bienestar = bienestar;
    }

    public void setEmprendimiento(String[] emprendimiento) {
        this.emprendimiento = emprendimiento;
    }
}
