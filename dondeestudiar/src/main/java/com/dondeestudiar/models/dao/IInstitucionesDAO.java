package com.dondeestudiar.models.dao;


import java.util.List;


import com.dondeestudiar.models.entities.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dondeestudiar.models.entities.Institucion;

@Repository("institucionesDAO")
public interface IInstitucionesDAO extends JpaRepository<Institucion, Integer> {

		Institucion findByRuc(String ruc);
		
		List<Institucion> findByNombreLikeIgnoreCase(String nombre);

		List<Institucion> findAllByOrderByNombreAsc();

		@Procedure
		void sp_disabledInstitucion( @Param("id") int id );
		
		@Procedure
		void sp_enabledInstitucion( @Param("id") int id );


	
}
