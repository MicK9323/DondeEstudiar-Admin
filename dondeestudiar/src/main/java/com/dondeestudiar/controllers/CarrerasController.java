package com.dondeestudiar.controllers;

import com.dondeestudiar.models.entities.Area;
import com.dondeestudiar.models.entities.Carrera;
import com.dondeestudiar.models.entities.Parametros;
import com.dondeestudiar.models.entities.Usuario;
import com.dondeestudiar.models.services.IAreaService;
import com.dondeestudiar.models.services.ICarreraService;
import com.dondeestudiar.models.services.IParametrosService;
import com.dondeestudiar.utils.Constantes;
import com.dondeestudiar.utils.UploadFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/carreras")
@SessionAttributes(names = {"carrera"})
public class CarrerasController {

    @Autowired
    ICarreraService carreraService;

    @Autowired
    IAreaService areaService;

    @Autowired
    IParametrosService parametrosService;

    @ModelAttribute
    public  Carrera setCarrera(){
        return  new Carrera();
    }

    //    Listar Carreras
    @GetMapping(value = "/listar")
    public String listar(Map<String, Object> model, RedirectAttributes flash, HttpServletRequest request) {

        if (!validarSession(request)) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }

        List<Carrera> carreras = carreraService.listarCarreras();
        model.put("carreras", carreras);
        model.put("titulo", "Listado de Carreras");
        return "admin/listaCarreras";
    }

    @GetMapping(value = "/nuevo")
    public String nuevo(Map<String, Object> model, RedirectAttributes flash, HttpServletRequest request) {
        if (!validarSession(request)) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }

        List<Area> areas = areaService.listarAreas();
        List<Parametros> tipoCarrera = parametrosService.findByIdGrupo(Constantes.TIPO_CARRERA);
        model.put("areas", areas);
        model.put("tipoCarrera", tipoCarrera);
        model.put("titulo", "Registrar Carrera");
        Carrera carrera = new Carrera();
        model.put("carrera", carrera);
        return "admin/regCarrera";
    }

    @PostMapping(value = "/nuevo")
    public String registrar(@Valid Carrera carrera, BindingResult result, SessionStatus status, Map<String, Object> model,
                            HttpServletRequest request, RedirectAttributes flash, MultipartFile file) {
        if (!validarSession(request)) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }

        if (result.hasErrors()) {
            List<Area> areas = areaService.listarAreas();
            List<Parametros> tipoCarrera = parametrosService.findByIdGrupo(Constantes.TIPO_CARRERA);
            model.put("areas", areas);
            model.put("tipoCarrera", tipoCarrera);
            model.put("error", Constantes.INVALID_DATA);
            model.put("titulo", "Editar Carrera");
            return "admin/regCarrera";
        }

        if (file.isEmpty() || file == null || file.getOriginalFilename().equals("")) {
            model.put("warning", Constantes.NO_IMAGE_SELECTED);
            List<Area> areas = areaService.listarAreas();
            List<Parametros> tipoCarrera = parametrosService.findByIdGrupo(Constantes.TIPO_CARRERA);
            model.put("areas", areas);
            model.put("tipoCarrera", tipoCarrera);
            model.put("titulo", "Editar Carrera");
            return "admin/regCarrera";
        }

        if (!file.isEmpty()) {
            String imagen = "";
            try {
                imagen = new UploadFiles().subirImagenFTP(file, Constantes.UPLOADS_CARRERAS);
                carrera.setRutaImagen(Constantes.URL_ENDPOINT + Constantes.UPLOADS_CARRERAS + imagen);
                carrera.setImagen(imagen);
                carreraService.guardar(carrera);
                flash.addFlashAttribute("success", Constantes.SAVE_SUCCESSFULL);
            } catch (Exception e) {
                model.put("error", e.getCause().toString() + " :" + e.getMessage());
                e.printStackTrace();
                List<Area> areas = areaService.listarAreas();
                List<Parametros> tipoCarrera = parametrosService.findByIdGrupo(Constantes.TIPO_CARRERA);
                model.put("areas", areas);
                model.put("tipoCarrera", tipoCarrera);
                model.put("titulo", "Editar Carrera");
                return "admin/regCarrera";
            }
        }

        status.setComplete();
        return "redirect:/carreras/listar";
    }

    @GetMapping(value = "/editar/{id}")
    public String ver(@PathVariable String id, HttpServletRequest request, Map<String, Object> model, RedirectAttributes flash){
        if( !validarSession(request) ){
            flash.addFlashAttribute("error",Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }

        Carrera carrera = carreraService.buscar(Integer.parseInt(id));
        List<Area> areas = areaService.listarAreas();
        List<Parametros> tipoCarrera = parametrosService.findByIdGrupo(Constantes.TIPO_CARRERA);
        model.put("areas", areas);
        model.put("tipoCarrera", tipoCarrera);
        model.put("carrera",carrera);
        model.put("titulo","Editar Carrera");
        return "admin/editCarrera";
    }

    @PostMapping(value = "/edit")
    public String editar(@Valid Carrera carrera, BindingResult result, SessionStatus status, Map<String, Object> model,
                         HttpServletRequest request, RedirectAttributes flash, MultipartFile file) {
        if(!validarSession(request)){
            flash.addFlashAttribute("error",Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }

        if( result.hasErrors() ){
            List<Area> areas = areaService.listarAreas();
            List<Parametros> tipoCarrera = parametrosService.findByIdGrupo(Constantes.TIPO_CARRERA);
            model.put("areas", areas);
            model.put("tipoCarrera", tipoCarrera);
            model.put("titulo", "Editar Carrera");
            model.put("error",Constantes.INVALID_DATA);
            return "admin/editCarrera";
        }

        if( !file.isEmpty() ){
            String imagen = "";
            try{
                if(carrera.getImagen().isEmpty()){
                    imagen = new UploadFiles().subirImagenFTP(file,Constantes.UPLOADS_CARRERAS);
                }else{
                    if( new UploadFiles().eliminarImagenFTP(Constantes.UPLOADS_CARRERAS,carrera.getImagen()) ){
                        imagen = new UploadFiles().subirImagenFTP(file,Constantes.UPLOADS_CARRERAS);
                    } //Agregar error si no se puede sobreescribir el fichero
                }
            }catch (Exception e){
                e.printStackTrace();
                model.put("error",e.getCause().toString()+": "+e.getMessage());
            }
            carrera.setRutaImagen(Constantes.URL_ENDPOINT+Constantes.UPLOADS_CARRERAS+imagen);
            carrera.setImagen(imagen);
            flash.addFlashAttribute("success",Constantes.CHANGES_SUCCESSFULL);
        }

        carreraService.guardar(carrera);
        status.setComplete();
        return "redirect:/carreras/listar";
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
