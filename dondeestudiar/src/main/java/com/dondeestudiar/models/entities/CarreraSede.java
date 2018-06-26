package com.dondeestudiar.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "det_sede_carrera")
public class CarreraSede implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CarreraSedePK id;

    @Column(name = "acreditado")
    private boolean acreditado;

    @Column(name = "costo_anual")
    private double costoAnual;

    @Column(name = "rel_ingresantes_postulantes")
    private int ingresantes;

    @ManyToOne
    @JoinColumn(name = "id_carrera",insertable=false, updatable = false)
    private Carrera carrera;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sede", insertable=false, updatable = false)
    @JsonIgnore
    private Sede sede;

    public CarreraSedePK getId() {
        return id;
    }

    public void setId(CarreraSedePK id) {
        this.id = id;
    }

    public boolean isAcreditado() {
        return acreditado;
    }

    public void setAcreditado(boolean acreditado) {
        this.acreditado = acreditado;
    }

    public double getCostoAnual() {
        return costoAnual;
    }

    public void setCostoAnual(double costoAnual) {
        this.costoAnual = costoAnual;
    }

    public int getIngresantes() {
        return ingresantes;
    }

    public void setIngresantes(int ingresantes) {
        this.ingresantes = ingresantes;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }
}
