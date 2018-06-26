package com.dondeestudiar.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_generales")
public class Parametros implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_param")
	private String idParam;
	
	@Column(name="id_grupo")
	private String idGrupo;
	
	@Column(name="desc_param")
	private String desc;
	
	@Column(name="estado")
	private boolean estado;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fec_reg")
	private Date fecReg;

	public String getIdParam() {
		return idParam;
	}

	public void setIdParam(String idParam) {
		this.idParam = idParam;
	}

	public String getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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
