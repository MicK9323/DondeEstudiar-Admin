package com.dondeestudiar.models.entities;

import com.dondeestudiar.utils.Constantes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_carreras")
public class Carrera implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrera")
    private int id;

    @Column(name = "nom_carrera")
    @NotEmpty
    @Size(min = 10, max = 100)
    @Pattern(regexp = "[A-Za-zÁÉÍÓÚñáéíóúÑ\\s?]{10,100}")
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_area")
    @JsonIgnore
    private Area area;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_carrera")
    @JsonIgnore
    private Parametros tipoCarrera;

    @Column(name = "duracion")
    @NotNull
    @Min(value = 1) @Max(value = 18)
    private int duracion;

    @Column(name = "popularidad")
    @NotNull @Min(value = 1)
    private int popularidad;

    @Column(name = "remuneracion_prom")
    @NotNull @Min(value = 950)
    private double remuneracion;

    @Column(name = "ruta_img")
    private String rutaImagen;

    @Column(name = "img_carrera")
    private String imagen;

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

    public boolean validar(Carrera obj){
        boolean flag = false;
        if(!obj.getNombre().matches("[A-Za-zÁÉÍÓÚñáéíóúÑ\\s?]{10,100}")){
            flag = false;
        }else if(!(obj.getArea().getId() > 1)){
            flag = false;
        }else if(!(obj.getTipoCarrera().getIdParam().matches("[TC]{1}[0-9]{3}"))){
            flag = false;
        }else if(!(obj.getDuracion() >= 6 && obj.getDuracion() <=24)){
            flag = false;
        }else if(!(obj.getPopularidad() >= 1)){
            flag = false;
        }else if(!(obj.getRemuneracion() >= 950)){
            flag = false;
        }else{
            flag = true;
        }
        return flag;
    }

    public String getSemestres(){
        return String.format("%s Semestres",this.duracion);
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
        this.nombre = nombre.toUpperCase().trim();
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Parametros getTipoCarrera() {
        return tipoCarrera;
    }

    public void setTipoCarrera(Parametros tipoCarrera) {
        this.tipoCarrera = tipoCarrera;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getPopularidad() {
        return popularidad;
    }

    public void setPopularidad(int popularidad) {
        this.popularidad = popularidad;
    }

    public double getRemuneracion() {
        return remuneracion;
    }

    public void setRemuneracion(double remuneracion) {
        this.remuneracion = remuneracion;
    }

    public String getRutaImagen() {
        if( this.rutaImagen.isEmpty() || this.rutaImagen.equals("") ){
            return Constantes.NOT_FOUND;
        }else{
            return rutaImagen;
        }
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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
