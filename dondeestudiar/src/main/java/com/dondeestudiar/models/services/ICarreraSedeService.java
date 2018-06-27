package com.dondeestudiar.models.services;

import com.dondeestudiar.models.entities.CarreraSede;
import com.dondeestudiar.models.entities.CarreraSedePK;

import java.util.List;

public interface ICarreraSedeService {

    List<CarreraSede> sp_carrerasSede(int id);

    boolean sp_validarAsignacion(int idCarrera, int idSede);

    void RegistrarDetalle(List<CarreraSede> detalle);

    void sp_retirarCarrera(int idCarrera, int idSede);

    CarreraSede buscarDetalle(int idCarrera, int idSede);

    void EditarDetalle(CarreraSede detalle);
}
