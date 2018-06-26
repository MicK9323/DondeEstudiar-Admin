package com.dondeestudiar.models.services.impl;

import com.dondeestudiar.models.dao.ISedeDAO;
import com.dondeestudiar.models.entities.Institucion;
import com.dondeestudiar.models.entities.Sede;
import com.dondeestudiar.models.services.ISedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SedeServiceImpl implements ISedeService {

    @Autowired
    ISedeDAO sedeDAO;

    @Override
    @Transactional(readOnly = true)
    public Sede buscarSede(int id) {
        return sedeDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void saveSede(Sede obj) {
        sedeDAO.save(obj);
    }

    @Override
    @Transactional
    public void disabledSede(int id) {
        sedeDAO.sp_disabledSede(id);
    }

    @Override
    @Transactional
    public void enabledSede(int id) {
        sedeDAO.sp_enabledSede(id);
    }

    @Override
    public Sede SaveAndFlush(Sede obj) {
        return sedeDAO.saveAndFlush(obj);
    }


}
