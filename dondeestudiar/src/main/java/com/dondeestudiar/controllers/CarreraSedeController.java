package com.dondeestudiar.controllers;

import com.dondeestudiar.models.entities.*;
import com.dondeestudiar.models.services.ICarreraSedeService;
import com.dondeestudiar.models.services.ICarreraService;
import com.dondeestudiar.models.services.IInstitucionesService;
import com.dondeestudiar.models.services.ISedeService;
import com.dondeestudiar.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/carreras")
@SessionAttributes(names = {"institucion"})
public class CarreraSedeController {
    @Autowired
    ICarreraService carreraService;

    @Autowired
    ISedeService sedeService;

    @Autowired
    IInstitucionesService institucionesService;

    @Autowired
    ICarreraSedeService carreraSedeService;

    // Carreras por Institución
    @GetMapping(value = "/mostrar/{id}")
    public String MostrarCarreras(@PathVariable String id, HttpServletRequest request, Map<String, Object> model, RedirectAttributes flash) {
        if( !validarSession(request) ){
            flash.addFlashAttribute("error",Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }
        Institucion institucion = institucionesService.findById(Integer.parseInt(id));
        List<Carrera> carreras = carreraService.sp_carrerasInstitucion(Integer.parseInt(id));
        if(carreras.isEmpty())
            model.put("info","No hay carreras registradas en esta institución");
        model.put("carreras",carreras);
        model.put("institucion",institucion);
        model.put("titulo","Carreras - "+institucion.getNombre());
        return "admin/carrerasInstitucion";
    }

    // Lista de carrera por sede
    @GetMapping(value = "/sede/{id}/mostrar")
    public String CarrerasPorSede(@PathVariable String id, HttpServletRequest request, Map<String, Object> model,
                                  RedirectAttributes flash){
        if(!validarSession(request)){
            flash.addFlashAttribute("error",Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }
        Sede sede = sedeService.buscarSede(Integer.parseInt(id));
        List<CarreraSede> carreras = carreraSedeService.sp_carrerasSede(Integer.parseInt(id));
        if(!carreras.isEmpty())
            model.put("lista",carreras);
        else
            model.put("vacio","No hay carreras registradas en esta sede");
        model.put("sede",sede);
        model.put("titulo","Carreras -"+sede.getInstitucion().getNombre());
        return "admin/verCarrerasSede";
    }

    // Validar asignacion
    @PostMapping(value = "/validar")
    @ResponseBody
    public boolean ValidarAsignacion(@RequestParam int idCarrera, @RequestParam int idSede){
        boolean valida = carreraSedeService.sp_validarAsignacion(idCarrera,idSede);
        return valida;
    }

    // Agregar carrera a institucion
    @GetMapping(value = "/institucion/asignar")
    public String AsignarCarreras(HttpServletRequest request, Map<String,Object> model,
                                  RedirectAttributes flash, @SessionAttribute Institucion institucion){
        if(!validarSession(request)){
            flash.addFlashAttribute("error",Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }
        List<Sede> sedes = institucion.getSedes();
        model.put("titulo","Asignar Carreras");
        model.put("institucion",institucion);
        model.put("sedes",sedes);
        return "admin/regCarreraSede";
    }

    // Registrar Detalle de Carreras y Sedes
    @PostMapping(value = "/detalle/guardar",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String RegistrarDetalle(@RequestBody List<HelperView> helpers, HttpServletRequest request
            , @SessionAttribute Institucion institucion){
        List<CarreraSede> detalles = new ArrayList<>();
        CarreraSede detalle = null;
        CarreraSedePK key = null;
        for(HelperView view : helpers){
            key = new CarreraSedePK();
            key.setIdCarrera(view.getIdCarrera());
            key.setIdSede(view.getIdSede());
            detalle = new CarreraSede();
            detalle.setId(key);
            detalle.setAcreditado(view.isAcreditado());
            detalle.setCostoAnual(view.getCosto());
            detalle.setIngresantes(view.getIngresantes());
            detalles.add(detalle);
        }
        carreraSedeService.RegistrarDetalle(detalles);
        return "/carreras/mostrar/"+institucion.getId();
    }

    // Cargar lista de helpers
/*    @PostMapping(value = "/cargar",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<HelperView> CargarLista(@RequestBody HelperView helper, HttpServletRequest request){
        List<HelperView> helpers = null;
        if(request.getSession().getAttribute("helpers") == null){
            helpers = new ArrayList<HelperView>();
            request.getSession().setAttribute("helpers",helpers);
        }else{
            helpers = (List<HelperView>) request.getSession().getAttribute("helpers");
        }
        try{
            helpers.add(helper);
            return helpers;
        }catch(Exception e){
            return null;
        }
    }*/

    // Autocompletar carreras
    @GetMapping(value = "/autocompletar/{term}")
    public String CargarCarreras(@PathVariable String term, Map<String,Object> model){
        term = term.replaceAll("_", " ");
        List<Carrera> carreras = carreraService.sp_cargarCarreras(term);
        model.put("carreras",carreras);
        return "admin/regCarreraSede :: #listaCarreras";
    }

    //    Validar Session
    public boolean validarSession(HttpServletRequest request) {
        if (request.getSession().getAttribute("logedusuario") == null) {
            return false;
        } else {
            return true;
        }
    }
}
