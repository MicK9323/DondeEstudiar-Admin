package com.dondeestudiar.models.services;

import com.dondeestudiar.models.entities.CarreraSede;

import java.util.List;

public interface ICarreraSedeService {

    List<CarreraSede> sp_carrerasSede(int id);

    boolean sp_validarAsignacion(int idCarrera, int idSede);

    void RegistrarDetalle(List<CarreraSede> detalle);
}
