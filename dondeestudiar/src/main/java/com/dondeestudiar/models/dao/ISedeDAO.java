package com.dondeestudiar.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dondeestudiar.models.entities.Sede;

@Repository("sedeDAO")
public interface ISedeDAO extends JpaRepository<Sede, Integer> {

    @Procedure
	public void sp_disabledSede(@Param("id") int id);

    @Procedure
    public void sp_enabledSede(@Param("id") int id);

}
