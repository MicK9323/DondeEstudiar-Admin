package com.dondeestudiar.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.dondeestudiar.models.entities.Ubigueo;

@Repository("ubigueoDAO")
public interface IUbigueoDAO extends JpaRepository<Ubigueo, String> {
	
	@Query(value = "{call sp_ubigeo(:vUbicacion)}", nativeQuery = true)
	public List<Ubigueo> execSpUbigueo( @Param("vUbicacion") String ubicacion );
	
	public Ubigueo findByCodUbigueo(String codUbigueo);
	
}
