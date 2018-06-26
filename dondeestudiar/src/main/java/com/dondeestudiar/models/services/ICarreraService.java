package com.dondeestudiar.models.services;

import com.dondeestudiar.models.entities.Carrera;

import javax.validation.Valid;
import java.util.List;

public interface ICarreraService {

    public List<Carrera> listarCarreras();

    public void guardar(Carrera obj);

    public Carrera buscar(int id);

    public boolean SaveAndVerify(Carrera obj);

    public List<Carrera> sp_carrerasInstitucion(int id);

    public List<Carrera> sp_cargarCarreras(String term);

}
