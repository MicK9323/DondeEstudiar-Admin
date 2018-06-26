package com.dondeestudiar.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dondeestudiar.models.entities.Parametros;

@Repository("parametrosDAO")
public interface IParametrosDAO extends JpaRepository<Parametros, String> {

	public List<Parametros> findByIdGrupo(String grupo);
	
}
