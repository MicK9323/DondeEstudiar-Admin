package com.dondeestudiar.models.dao;

import com.dondeestudiar.models.entities.CarreraSede;
import org.hibernate.annotations.Parameter;
import org.hibernate.result.Output;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICarreraSedeDAO extends JpaRepository<CarreraSede, Integer> {

    @Query(nativeQuery = true, value = "execute sp_carrerasSede :id")
    List<CarreraSede> sp_carrerasSede(@Param("id") int id );

    @Query(nativeQuery = true, value = "execute sp_validarAsignacion :idCarrera, :idSede")
    int sp_validarAsignacion(@Param("idCarrera") int idCarrera, @Param("idSede") int idSede);

}
