package com.dondeestudiar.models.services;

import java.util.List;

import com.dondeestudiar.models.entities.Parametros;

public interface IParametrosService {
	
	public List<Parametros> findByIdGrupo(String grupo);

}
