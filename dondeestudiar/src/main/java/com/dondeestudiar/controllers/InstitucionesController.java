package com.dondeestudiar.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dondeestudiar.models.entities.Institucion;
import com.dondeestudiar.models.entities.Parametros;
import com.dondeestudiar.models.entities.Sede;
import com.dondeestudiar.models.entities.Ubigueo;
import com.dondeestudiar.models.services.IInstitucionesService;
import com.dondeestudiar.models.services.IParametrosService;
import com.dondeestudiar.models.services.IUbigueoService;
import com.dondeestudiar.utils.Constantes;
import com.dondeestudiar.utils.G;
import com.dondeestudiar.utils.UploadFiles;

@Controller
@RequestMapping("/instituciones")
@SessionAttributes(names = {"institucion"})
public class InstitucionesController {

    @Autowired
    IParametrosService parametrosService;

    @Autowired
    IUbigueoService ubigueoService;

    @Autowired
    IInstitucionesService institucionesService;

    @ModelAttribute("institucion")
    public Institucion setInstitucion() {
        return new Institucion();
    }

    // Autocompletar ubigueos
    @GetMapping(value = "/cargar-ubigueo/{term}")
    public String cargarUbigueo(@PathVariable String term, Map<String, Object> model) {
        term = term.replaceAll("_", " ");
        List<Ubigueo> ubigueos = ubigueoService.execSpUbigueo(term);
        model.put("ubigueos", ubigueos);
        return "admin/regInstitucion :: #listaUbigueos";
    }

    // Listado de instituciones
    @GetMapping(value = "/listar")
    public String listar(Map<String, Object> model, HttpServletRequest request, RedirectAttributes flash) {
        if (validarSesion(request) == false) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }
        List<Institucion> listado = institucionesService.listarInstituciones();
        List<Parametros> tipoInstitucion = parametrosService.findByIdGrupo(Constantes.TIPO_INSTITUCION);
        model.put("listado", listado);
        model.put("parametros", tipoInstitucion);
        model.put("titulo", "Listado de Instituciones");
        return "admin/listaInstituciones";
    }

    // Ver detalle de institucion
    @GetMapping(value = "/ver/{ruc}")
    public String verInstitucion(@PathVariable String ruc, Map<String, Object> model, HttpServletRequest request,
                                 RedirectAttributes flash) {
        if (validarSesion(request) == false) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }
        Institucion institucion = institucionesService.findByRuc(ruc);
        if (institucion.getSedes().isEmpty()){
            model.put("info",Constantes.NO_FOUND_SEDES);
        }
        model.put("titulo", "Detalle Institucion");
        model.put("institucion", institucion);
        return "admin/verInstitucion";
    }

    // LLamar a vista de registrar Institucion
    @SuppressWarnings("unchecked")
    @GetMapping(value = "/nuevo")
    public String nuevo(HttpServletRequest request, Map<String, Object> model, RedirectAttributes flash) {
        if(!validarSesion(request)){
            flash.addFlashAttribute("error",Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }
        if(request.getSession().getAttribute("sedes") != null) {
            request.getSession().removeAttribute("sedes");
        }
        Institucion institucion = new Institucion();
        List<Parametros> tipoInstitucion = parametrosService.findByIdGrupo(Constantes.TIPO_INSTITUCION);
        List<Parametros> tipoGestion = parametrosService.findByIdGrupo(Constantes.TIPO_GESTION);
        model.put("tipoInstitucion", tipoInstitucion);
        model.put("tipoGestion", tipoGestion);
        model.put("institucion", institucion);
        model.put("titulo", "Nueva Institución");
        return "admin/regInstitucion";
    }

    @PostMapping(value = "/agregarsede")
    @ResponseBody
    public List<Sede> AgregarSede(@RequestParam String nomSede, @RequestParam String ubigueo,
                              @RequestParam String direccion, @RequestParam String telf,
                              @RequestParam String cx, @RequestParam String cy, HttpServletRequest request,
                              Map<String,Object> model, RedirectAttributes flash){
        List<Sede> sedes = null;
        if(request.getSession().getAttribute("sedes") == null){
            sedes = new ArrayList<Sede>();
            request.getSession().setAttribute("sedes",sedes);
        }else {
            sedes = (ArrayList<Sede>) request.getSession().getAttribute("sedes");
        }
        try{
            Sede sede = new Sede();
            Ubigueo ubicacion = new Ubigueo();
            ubicacion.setCodUbigueo(ubigueo);
            sede.setNomSede(nomSede);
            sede.setUbicacion(ubicacion);
            sede.setDireccion(direccion);
            sede.setTelf(telf);
            sede.setCx(cx);
            sede.setCy(cy);
            sedes.add(sede);
            return sedes;
        }catch(Exception e){
            model.put("sedeError",String.format("Error: %s \n Mensaje: %s",e.getCause().toString(),e.getMessage()));
            return null;
        }
    }

    @GetMapping(value = "/delsede/{index}")
    @ResponseBody
    public List<Sede> RetirarSede(@PathVariable String index, HttpServletRequest request){
        List<Sede> sedes = (List<Sede>) request.getSession().getAttribute("sedes");
        int indice = Integer.parseInt(index)-1;
        sedes.remove(indice);
        return sedes;
    }

    // Registrar una Institucion y sus sedes
    @SuppressWarnings("unchecked")
    @PostMapping(value = "/nuevo")
    public String registrar(@Valid Institucion institucion, BindingResult result, SessionStatus status, HttpServletRequest request,
                            HttpSession session, @RequestParam("file") MultipartFile file, Model model, RedirectAttributes flash) {

        if (validarSesion(request) == false) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }
        List<Parametros> tipoInstitucion = parametrosService.findByIdGrupo(Constantes.TIPO_INSTITUCION);
        List<Parametros> tipoGestion = parametrosService.findByIdGrupo(Constantes.TIPO_GESTION);
        model.addAttribute("tipoInstitucion", tipoInstitucion);
        model.addAttribute("tipoGestion", tipoGestion);
        model.addAttribute("listaSedes", (ArrayList<Sede>) session.getAttribute("sedes"));
        model.addAttribute("titulo", "Nueva Institución");

        if (result.hasErrors()) {
            return "admin/regInstitucion";
        }

        if (session.getAttribute("sedes") == null) {
            model.addAttribute("warning", Constantes.NO_FOUND_SEDES);
            return "admin/regInstitucion";
        }

        if (file.isEmpty() || file.getName().equals("")) {
            model.addAttribute("warning", Constantes.NO_IMAGE_SELECTED);
            return "admin/regInstitucion";
        }

        String foto = "";
        Institucion persistObj = null;
        if (!file.isEmpty()) {
            try {
                foto = new UploadFiles().subirImagenFTP(file, Constantes.UPLOADS_INSTITUCIONES);
                institucion.setRutaLogo(Constantes.URL_ENDPOINT + Constantes.UPLOADS_INSTITUCIONES + foto);
                institucion.setLogo(foto);
                persistObj = institucionesService.addInstitucion(institucion);
                if (persistObj != null) {
                    ArrayList<Sede> sedes = (ArrayList<Sede>) session.getAttribute("sedes");
                    for (Sede obj : sedes) {
                        obj.setInstitucion(persistObj);
                    }
                    institucionesService.addSedes(sedes);
                }
                session.removeAttribute("sedes");
                flash.addFlashAttribute("success", Constantes.SAVE_SUCCESSFULL);
            } catch (IOException e) {
                model.addAttribute("error", e.getClass() + " " + e.getCause().toString());
                e.printStackTrace();
                return "admin/regInstitucion";
            }
        }
        status.setComplete();
        return "redirect:/instituciones/ver/"+persistObj.getRuc();
    }

    // Llamar a vista Editar Institucion
    @GetMapping(value = "/editar/{ruc}")
    public String editarInstitucion(@PathVariable String ruc, Map<String, Object> model, HttpServletRequest request,
                                    RedirectAttributes flash) {
        if (validarSesion(request) == false) {
            flash.addFlashAttribute("error", "Inicie sesion antes de continuar");
            return "redirect:/admin/login";
        }
        Institucion institucion = institucionesService.findByRuc(ruc);
        model.put("titulo", "Editar Institucion");
        model.put("institucion", institucion);
        return "admin/editInstitucion";
    }

    // Editar institucion
    @PostMapping(value = "/editar")
    public String mergeInstitucion(@Valid Institucion institucion, BindingResult result, HttpServletRequest request,
                                   SessionStatus status, @RequestParam("file") MultipartFile file, Map<String, Object> model, RedirectAttributes flash) {
        if (validarSesion(request) == false) {
            flash.addFlashAttribute("error", "Inicie sesion antes de continuar");
            return "redirect:/admin/login";
        }

        if (result.hasErrors()) {
            model.put("titulo", "Nueva Institución");
            return "admin/editInstitucion";
        }

        if (!file.isEmpty()) {
            String foto = "";
            try {
                if (institucion.getLogo().isEmpty()) {
                    foto = new UploadFiles().subirImagenFTP(file, Constantes.UPLOADS_INSTITUCIONES);
                } else {
                    if (new UploadFiles().eliminarImagenFTP(Constantes.UPLOADS_INSTITUCIONES, institucion.getLogo()))
                        foto = new UploadFiles().subirImagenFTP(file, Constantes.UPLOADS_INSTITUCIONES);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            institucion.setRutaLogo(Constantes.URL_ENDPOINT + Constantes.UPLOADS_INSTITUCIONES + foto);
            institucion.setLogo(foto);
        }

        institucionesService.saveInstitucion(institucion);
        String ruc = institucion.getRuc();
        status.setComplete();
        flash.addFlashAttribute("success", "Institución editada correctamente");
        return "redirect:/instituciones/ver/" + ruc;
    }

    //	Deshabilitar institucion
    @GetMapping(value = "/disabled/{id}")
    public String disabled(@PathVariable String id, RedirectAttributes flash) {
        int ID = Integer.parseInt(id);
        institucionesService.disabledInstitucion(ID);
        flash.addFlashAttribute("success", "Institucion deshabilitada");
        return "redirect:/instituciones/listar";
    }

    //	Habilitar institucion
    @GetMapping(value = "/enabled/{id}")
    public String enabled(@PathVariable String id, RedirectAttributes flash) {
        int ID = Integer.parseInt(id);
        institucionesService.enabledInstitucion(ID);
        flash.addFlashAttribute("success", "Institucion habilitada");
        return "redirect:/instituciones/listar";
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
