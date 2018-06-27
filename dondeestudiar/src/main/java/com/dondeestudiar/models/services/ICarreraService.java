package com.dondeestudiar.models.services;

import com.dondeestudiar.models.entities.Carrera;

import javax.validation.Valid;
import java.util.List;

public interface ICarreraService {

    List<Carrera> listarCarreras();

    void guardar(Carrera obj);

    Carrera buscar(int id);

    boolean SaveAndVerify(Carrera obj);

    List<Carrera> sp_carrerasInstitucion(int id);

    List<Carrera> sp_cargarCarreras(String term);

    void sp_disabledCarrera(String idCarrera);

    void sp_enabledCarrera(String idCarrera);

    boolean sp_nombreCarrera(String nomCarrera);

}
