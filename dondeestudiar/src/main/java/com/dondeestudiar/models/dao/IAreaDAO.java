package com.dondeestudiar.models.dao;

import com.dondeestudiar.models.entities.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("areaDAO")
public interface IAreaDAO extends JpaRepository<Area, Integer> {



}
