package com.dondeestudiar.models.entities;

import com.dondeestudiar.utils.Constantes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_usuarios")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Pattern(regexp = "[1-9]{1}[0-9]{7}")
	private String dni_user;

	@NotEmpty
	@Pattern(regexp = "[A-Za-zÁÉÍÓÚñáéíóúÑ\\s?]{3,30}")
	private String nom_user;

	@NotEmpty
	@Pattern(regexp = "[A-Za-zÁÉÍÓÚñáéíóúÑ\\s?]{3,30}")
	private String ape_user;

	@NotEmpty
	@Pattern(regexp = "[0-9A-Za-zÁÉÍÓÚñáéíóúÑ]{5,8}")
	private String usuario;

	@Size(min = 5, max = 8)
	@Pattern(regexp = "[0-9A-Za-zÁÉÍÓÚñáéíóúÑ]{5,8}")
	private String clave;

	@Column(name = "ruta_foto")
	private String rutaFoto;

	private String foto;

	@NotNull
	private boolean estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fec_reg", updatable = false)
	private Date fec_reg;

	@ManyToOne
	@JoinColumn(name = "id_rol")
	private Rol rol;

	@PrePersist
	private void PrePersist(){
		Rol role = new Rol();
		role.setId_rol(1);
		this.fec_reg = new Date();
		this.rol = role;
		this.clave = this.dni_user;
		this.estado = true;
	}

	public String getNombres() {
		return String.format("%s %s", this.nom_user, this.ape_user);
	}
	
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getDni_user() {
		return dni_user;
	}

	public void setDni_user(String dni_user) {
		this.dni_user = dni_user;
	}

	public String getNom_user() {
		return nom_user;
	}

	public void setNom_user(String nom_user) {
		this.nom_user = nom_user;
	}

	public String getApe_user() {
		return ape_user;
	}

	public void setApe_user(String ape_user) {
		this.ape_user = ape_user;
	}

	public String getUsuario() {
		if(this.usuario == null)
			return usuario;
		else
			return usuario.trim();
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario.trim();
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Date getFec_reg() {
		return fec_reg;
	}

	public void setFec_reg(Date fec_reg) {
		this.fec_reg = fec_reg;
	}

	public String getRutaFoto() {
		if(this.rutaFoto.isEmpty() || this.rutaFoto.equals("") || this.rutaFoto == null){
			return Constantes.NOT_USER_IMAGE;
		}
		return rutaFoto;
	}

	public void setRutaFoto(String rutaFoto) {
		this.rutaFoto = rutaFoto;
	}
}
