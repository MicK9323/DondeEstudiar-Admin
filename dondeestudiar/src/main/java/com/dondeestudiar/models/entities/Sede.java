package com.dondeestudiar.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="tb_sedes")
public class Sede implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sede")
	private int id;
	
	@Column(name="nom_sede")
	@NotEmpty @Size(min = 10, max = 100)
	@Pattern(regexp = "[A-Za-zÁÉÍÓÚñáéíóúÑ\\s?]{10,100}")
	private String nomSede;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_ubigueo")
	private Ubigueo ubicacion;
	
	@Column(name="direccion")
	@NotEmpty
	@Size(min = 10, max = 120)
	private String direccion;
	
	@Column(name="telf")
	@NotEmpty
	@Size(min = 7, max = 15)
	@Pattern(regexp = "[0-9]{7,15}")
	private String telf;
	
	@Column(name="coordenada_x")
	private String cx;
	
	@Column(name="coordenada_y")
	private String cy;
	
	@Column(name="estado")
	private boolean estado;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fec_reg")
	private Date fecReg;
	
	@ManyToOne(fetch = FetchType.LAZY )
	@LazyToOne(LazyToOneOption.FALSE)
	@JoinColumn(name="id_institucion")
	private Institucion institucion;

	@OneToMany(mappedBy = "sede", fetch = FetchType.LAZY)
	private List<CarreraSede> carreras;

	@PrePersist
	private void prePersist() {
		this.estado = true;
		this.fecReg = new Date();
	}

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomSede() {
		return nomSede;
	}

	public void setNomSede(String nomSede) {
		this.nomSede = nomSede;
	}

	public Ubigueo getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubigueo ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelf() {
		return telf;
	}

	public void setTelf(String telf) {
		this.telf = telf;
	}

	public String getCx() {
		return cx;
	}

	public void setCx(String cx) {
		this.cx = cx;
	}

	public String getCy() {
		return cy;
	}

	public void setCy(String cy) {
		this.cy = cy;
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
