package com.dondeestudiar.models.services;

import com.dondeestudiar.models.entities.Sede;

public interface ISedeService {

    public Sede buscarSede(int id);

    public void saveSede(Sede obj);

    public void disabledSede(int id);

    public void enabledSede(int id);

	public Sede SaveAndFlush(Sede obj);

}
