package com.dondeestudiar.controllers;

import com.dondeestudiar.models.entities.Institucion;
import com.dondeestudiar.models.entities.Sede;
import com.dondeestudiar.models.entities.Ubigueo;
import com.dondeestudiar.models.services.IInstitucionesService;
import com.dondeestudiar.models.services.ISedeService;
import com.dondeestudiar.models.services.IUbigueoService;
import com.dondeestudiar.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/sedes")
@SessionAttributes(names = {"sede","institucion"})
public class SedesController {

    @Autowired
    ISedeService sedeService;

    @Autowired
    IInstitucionesService institucionesService;

    @Autowired
    IUbigueoService ubigueoService;

    @ModelAttribute
    public Sede setSede() {
        return new Sede();
    }

    @GetMapping(value = "/ubigueos/{term}")
    public String cargarUbigueo(@PathVariable String term, Map<String, Object> model) {
        term = term.replaceAll("_", " ");
        List<Ubigueo> ubigueos = ubigueoService.execSpUbigueo(term);
        model.put("ubigueos", ubigueos);
        return "admin/regSede :: #listaUbigueos";
    }

    @GetMapping(value = "/{ruc}/nuevo")
    public String NuevaSede(@PathVariable String ruc, Map<String, Object> model, RedirectAttributes flash,
                            HttpServletRequest request) {
        if (!validarSesion(request)) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }
        Institucion institucion = institucionesService.findByRuc(ruc);
        Sede sede = new Sede();
        model.put("institucion", institucion);
        model.put("titulo", "Registrar Sede");
        return "admin/regSede";
    }

    @PostMapping(value = "/registrar")
    public String RegistrarSede(@Valid Sede sede, BindingResult result, HttpServletRequest request,
                                Map<String, Object> model, SessionStatus status, RedirectAttributes flash,
                                @SessionAttribute Institucion institucion) {
        if (!validarSesion(request)) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }

        if (result.hasErrors()) {
            model.put("titulo", "Detalle de Sede");
            return "admin/regSede";
        }

        Sede persistObj = null;
        try{
            sede.setInstitucion(institucion);
            persistObj = sedeService.SaveAndFlush(sede);
            status.setComplete();
            flash.addFlashAttribute("success", Constantes.SAVE_SUCCESSFULL);
            return "redirect:/instituciones/ver/"+persistObj.getInstitucion().getRuc();
        }catch (Exception e){
               model.put("error",String.format("Error: %s - Detalle: %s",e.getCause().toString(),e.getMessage()));
               return "admin/regSede";
        }
    }

    //    Ir a vista ver sede
    @GetMapping(value = "/ver/{id}")
    public String ver(@PathVariable String id, Map<String, Object> model, HttpServletRequest request, RedirectAttributes flash) {
        if (!validarSesion(request)) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }
        Sede sede = sedeService.buscarSede(Integer.parseInt(id));
        model.put("titulo", "Detalle de Sede");
        model.put("sede", sede);
        return "admin/editSede";
    }

    //Editar Sede
    @PostMapping(value = "/editar")
    public String editar(@Valid Sede sede, BindingResult result, HttpServletRequest request,
                         Map<String, Object> model, SessionStatus status, RedirectAttributes flash) {

        if (!validarSesion(request)) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }

        if (result.hasErrors()) {
            model.put("titulo", "Detalle de Sede");
            return "admin/editSede";
        }

        int id = sede.getId();

        if (sede.getId() >= 1) {
            sedeService.saveSede(sede);
            status.setComplete();
            flash.addFlashAttribute("success", "Se editaron los datos de la sede correctamente");
            return "redirect:/sedes/ver/" + id;
        } else {
            status.setComplete();
            flash.addFlashAttribute("error", "Ocurrió un error al cargar los datos de la sede");
            return "redirect:/sedes/ver/" + id;
        }
    }

    //    Desabilitar sede
    @GetMapping(value = "/disabled/{id}/{ruc}")
    public String disabledSede(@PathVariable String id, @PathVariable String ruc, RedirectAttributes flash) {
        int idSede = Integer.parseInt(id);
        sedeService.disabledSede(idSede);
        flash.addFlashAttribute("success", "Sede Deshabilitada");
        return "redirect:/instituciones/ver/" + ruc;
    }

    //    Habilitar Sede
    @GetMapping(value = "/enabled/{id}/{ruc}")
    public String enabledSede(@PathVariable String id, @PathVariable String ruc, RedirectAttributes flash) {
        int idSede = Integer.parseInt(id);
        sedeService.enabledSede(idSede);
        flash.addFlashAttribute("success", "Sede Habilitada");
        return "redirect:/instituciones/ver/" + ruc;
    }


    // Validar si existe sesion
    private boolean validarSesion(HttpServletRequest request) {
        if (request.getSession().getAttribute("logedusuario") == null) {
            return false;
        } else {
            return true;
        }
    }

}
