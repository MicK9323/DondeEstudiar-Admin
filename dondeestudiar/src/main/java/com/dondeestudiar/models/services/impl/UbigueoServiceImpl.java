package com.dondeestudiar.models.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dondeestudiar.models.dao.IUbigueoDAO;
import com.dondeestudiar.models.entities.Ubigueo;
import com.dondeestudiar.models.services.IUbigueoService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UbigueoServiceImpl implements IUbigueoService {
	
	@Autowired
	IUbigueoDAO ubigueoDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Ubigueo> execSpUbigueo(String ubicacion) {
		return ubigueoDAO.execSpUbigueo(ubicacion);
	}

	@Override
	@Transactional(readOnly = true)
	public Ubigueo findByCodUbigueo(String codUbigueo) {
		return ubigueoDAO.findByCodUbigueo(codUbigueo);
	}

}
