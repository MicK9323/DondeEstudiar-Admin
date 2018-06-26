package com.dondeestudiar.models.services.impl;

import com.dondeestudiar.models.dao.ICarreraSedeDAO;
import com.dondeestudiar.models.entities.CarreraSede;
import com.dondeestudiar.models.services.ICarreraSedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraSedeServiceImpl implements ICarreraSedeService {

    @Autowired
    ICarreraSedeDAO carreraSedeDAO;

    @Override
    public List<CarreraSede> sp_carrerasSede(int id) {
        return carreraSedeDAO.sp_carrerasSede(id);
    }

    @Override
    public boolean sp_validarAsignacion(int idCarrera, int idSede) {
       int flag = carreraSedeDAO.sp_validarAsignacion(idCarrera,idSede);
       if(flag == 1)
           return true;
       else
           return false;
    }

    @Override
    public void RegistrarDetalle(List<CarreraSede> detalle) {
        carreraSedeDAO.saveAll(detalle);
    }

}
