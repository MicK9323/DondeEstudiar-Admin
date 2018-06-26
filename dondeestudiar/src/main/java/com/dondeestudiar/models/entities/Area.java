package com.dondeestudiar.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_areas")
public class Area implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area")
    private int id;

    @NotNull @NotEmpty
    @Pattern(regexp = "[A-Za-zÁÉÍÓÚñáéíóúÑ\\s?]{5,100}")
    @Column(name = "desc_area")
    private String nombre;

    @Column(name = "estado")
    private boolean estado;

    @Temporal(TemporalType.DATE)
    @Column(name = "fec_reg")
    private Date fecReg;

    @PrePersist
    private void prePersist(){
        this.setEstado(true);
        this.setFecReg(new Date());
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFecReg() {
        return fecReg;
    }

    public void setFecReg(Date fecReg) {
        this.fecReg = fecReg;
    }
}
