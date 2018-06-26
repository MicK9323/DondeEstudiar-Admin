package com.dondeestudiar.controllers;

import com.dondeestudiar.models.entities.Area;
import com.dondeestudiar.models.services.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/area")
public class AreaController {

    @Autowired
    IAreaService areaService;

    @GetMapping(value = "/listar")
    public String listar(Map<String,Object> model){
        List<Area> areas = areaService.listarAreas();
        model.put("areas",areas);
        return null;
    }

}
