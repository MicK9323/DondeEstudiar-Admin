package com.dondeestudiar.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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
    @Min(0)
    private double costoAnual;

    @Column(name = "rel_ingresantes_postulantes")
    @Min(10) @Max(100)
    private int ingresantes;

    @ManyToOne
    @JoinColumn(name = "id_carrera",insertable=false, updatable = false)
    private Carrera carrera;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sede", insertable=false, updatable = false)
    @JsonIgnore
    private Sede sede;

    @Column(name = "identificador", updatable = false)
    private String identificador;

    @PrePersist
    private void PrePersist(){
        String idCarrera = ""+this.id.getIdCarrera();
        String idSede = ""+this.id.getIdSede();
        this.identificador = idCarrera+idSede;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

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
