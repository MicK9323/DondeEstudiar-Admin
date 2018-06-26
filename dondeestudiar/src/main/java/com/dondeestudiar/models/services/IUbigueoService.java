package com.dondeestudiar.models.services;

import java.util.List;

import com.dondeestudiar.models.entities.Ubigueo;

public interface IUbigueoService {
	
	public List<Ubigueo> execSpUbigueo( String ubicacion );
	
	public Ubigueo findByCodUbigueo(String codUbigueo);
	
}
