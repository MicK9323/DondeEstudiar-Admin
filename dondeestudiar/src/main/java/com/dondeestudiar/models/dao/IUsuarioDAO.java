package com.dondeestudiar.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dondeestudiar.models.entities.Usuario;

@Repository("usuarioDAO")
public interface IUsuarioDAO extends JpaRepository<Usuario, String> {
	

	@Query(value = "{call sp_login(:vUsuario, :vClave)}", nativeQuery = true)
	Usuario login( @Param("vUsuario") String usuario,
			@Param("vClave") String clave);

	@Procedure
	void sp_disabledUsuario(@Param("dni") String dni);

	@Procedure
	void sp_enabledUsuario(@Param("dni") String dni);

	Usuario findByUsuario(String usuario);
	
}
