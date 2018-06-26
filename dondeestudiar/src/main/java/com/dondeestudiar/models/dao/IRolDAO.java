package com.dondeestudiar.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dondeestudiar.models.entities.Rol;

@Repository("rolDAO")
public interface IRolDAO extends JpaRepository<Rol, Integer> {
	
	public Rol findByRole(String role);
	
}
