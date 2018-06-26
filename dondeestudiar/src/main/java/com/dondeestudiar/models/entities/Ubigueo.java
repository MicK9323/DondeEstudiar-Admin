package com.dondeestudiar.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_ubigueos")
public class Ubigueo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cod_ubigueo")
	private String codUbigueo;
	
	@Column(name="cod_dep")
	private String codDep;
	
	@Column(name="desc_dep")
	private String descDep;
	
	@Column(name="cod_prov")
	private String codProv;
	
	@Column(name="desc_prov")
	private String descProv;
	
	@Column(name="cod_dist")
	private String codDist;
	
	@Column(name="desc_dist")
	private String descDist;
	
	public String getDescripcion() {
		return String.format("%s, %s, %s", this.descDist, this.descProv, this.descDep);
	}
	
	public String getCodUbigueo() {
		return codUbigueo;
	}
	public void setCodUbigueo(String codUbigueo) {
		this.codUbigueo = codUbigueo;
	}
	public String getCodDep() {
		return codDep;
	}
	public void setCodDep(String codDep) {
		this.codDep = codDep;
	}
	public String getDescDep() {
		return descDep;
	}
	public void setDescDep(String descDep) {
		this.descDep = descDep;
	}
	public String getCodProv() {
		return codProv;
	}
	public void setCodProv(String codProv) {
		this.codProv = codProv;
	}
	public String getDescProv() {
		return descProv;
	}
	public void setDescProv(String descProv) {
		this.descProv = descProv;
	}
	public String getCodDist() {
		return codDist;
	}
	public void setCodDist(String codDist) {
		this.codDist = codDist;
	}
	public String getDescDist() {
		return descDist;
	}
	public void setDescDist(String descDist) {
		this.descDist = descDist;
	}	
	
}
