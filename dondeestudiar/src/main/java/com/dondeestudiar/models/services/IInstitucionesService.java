package com.dondeestudiar.models.services;

import java.util.ArrayList;
import java.util.List;

import com.dondeestudiar.models.entities.Carrera;
import com.dondeestudiar.models.entities.Institucion;
import com.dondeestudiar.models.entities.Sede;

public interface IInstitucionesService {
	
	public List<Institucion> listarInstituciones();
	
	public Institucion addInstitucion(Institucion obj);
	
	public void saveInstitucion(Institucion obj);
	
	public Institucion findByRuc(String ruc);

	public Institucion findById(int id);
	
	public void addSedes(ArrayList<Sede> sedes);
	
	public List<Institucion> findByNombre(String nombre);
	
	public void disabledInstitucion( int id );
	
	public void enabledInstitucion( int id );

	boolean SaveAndVerify(Institucion obj);
	
}
