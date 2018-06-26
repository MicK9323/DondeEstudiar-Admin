package com.dondeestudiar.models.dao;

import com.dondeestudiar.models.entities.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("carreraDAO")
public interface ICarreraDAO extends JpaRepository<Carrera, Integer> {

    public List<Carrera> findAllByOrderByNombreAsc();

    @Query(nativeQuery = true, value = "execute sp_carrerasInstitucion :id")
    public List<Carrera> sp_carrerasInstitucion( @Param("id") int id );

    @Query(nativeQuery = true, value = "execute sp_cargarCarreras :term")
    public List<Carrera> sp_cargarCarreras( @Param("term") String term );

}
