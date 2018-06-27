package com.dondeestudiar.models.services;

import java.util.ArrayList;
import java.util.List;

import com.dondeestudiar.models.entities.Carrera;
import com.dondeestudiar.models.entities.Institucion;
import com.dondeestudiar.models.entities.Sede;

public interface IInstitucionesService {
	
	List<Institucion> listarInstituciones();
	
	Institucion addInstitucion(Institucion obj);
	
	void saveInstitucion(Institucion obj);
	
	Institucion findByRuc(String ruc);

	Institucion findById(int id);
	
	void addSedes(ArrayList<Sede> sedes);
	
	List<Institucion> findByNombre(String nombre);
	
	void disabledInstitucion( int id );
	
	void enabledInstitucion( int id );

	boolean validarRuc(String ruc);

	boolean SaveAndVerify(Institucion obj);

	boolean sp_validarInstitucion(String nombre);
	
}
