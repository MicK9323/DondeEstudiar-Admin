package com.dondeestudiar.models.services.impl;

import com.dondeestudiar.models.dao.IAreaDAO;
import com.dondeestudiar.models.entities.Area;
import com.dondeestudiar.models.services.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements IAreaService {

    @Autowired
    IAreaDAO areaDAO;

    @Override
    public List<Area> listarAreas() {
        return areaDAO.findAll();
    }
}
