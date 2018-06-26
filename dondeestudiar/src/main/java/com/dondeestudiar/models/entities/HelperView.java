package com.dondeestudiar.models.entities;

import java.io.Serializable;

public class HelperView implements Serializable {

    private int idCarrera;
    private String nomCarrera;
    private int idSede;
    private String nomSede;
    private boolean acreditado;
    private double costo;
    private int ingresantes;

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNomCarrera() {
        return nomCarrera;
    }

    public void setNomCarrera(String nomCarrera) {
        this.nomCarrera = nomCarrera;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public String getNomSede() {
        return nomSede;
    }

    public void setNomSede(String nomSede) {
        this.nomSede = nomSede;
    }

    public boolean isAcreditado() {
        return acreditado;
    }

    public void setAcreditado(boolean acreditado) {
        this.acreditado = acreditado;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getIngresantes() {
        return ingresantes;
    }

    public void setIngresantes(int ingresantes) {
        this.ingresantes = ingresantes;
    }
}
