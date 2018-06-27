package com.dondeestudiar.controllers;

import com.dondeestudiar.models.entities.Usuario;
import com.dondeestudiar.models.services.IUsuarioService;
import com.dondeestudiar.utils.Constantes;
import com.dondeestudiar.utils.UploadFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/usuarios")
@SessionAttributes(names = {"usuario"})
public class UsuarioController {

    @Autowired
    IUsuarioService usuarioService;

    // Lista de usuarios
    @GetMapping(value = "/lista")
    public String ListUsuarios(HttpServletRequest request, Map<String, Object> model, RedirectAttributes flash) {
        if (!validarSesion(request)) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }
        List<Usuario> usuarios = usuarioService.listAll();
        model.put("titulo", "Listado de Usuarios");
        model.put("usuarios", usuarios);
        return "admin/listaUsuarios";
    }

    // Validar que no se repita dni
    @PostMapping(value = "/validardni")
    @ResponseBody
    public boolean ValidarDni(@RequestParam String dni){
        boolean valida = usuarioService.validarDni(dni);
        return valida;
    }

    // Validar que no se repita nombre de usuario
    @PostMapping(value = "/validarusuario")
    @ResponseBody
    public boolean ValidarUsuario(@RequestParam String usuario){
        boolean valida = usuarioService.validarUsuario(usuario);
        return valida;
    }

    // Registrar Usuario
    @GetMapping(value = "/registrar")
    public String RegistrarUsuario(HttpServletRequest request, Map<String, Object> model,
                                   RedirectAttributes flash) {
        if (!validarSesion(request)) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }
        Usuario usuario = new Usuario();
        model.put("usuario", usuario);
        model.put("titulo", "Registrar Administrador");
        return "admin/regUsuario";
    }

    // Registrar administrador
    @PostMapping(value = "/nuevo")
    public String NuevoAdministrador(@Valid Usuario usuario, BindingResult result, RedirectAttributes flash,
                                     SessionStatus status, HttpServletRequest request, MultipartFile file, Map<String, Object> model) {
        if (!validarSesion(request)) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }

        if (result.hasErrors()) {
            model.put("error", Constantes.INVALID_DATA);
            model.put("titulo","Registrar Usuario");
            return "admin/regUsuario";
        }

        if (file.isEmpty() || file == null) {
            model.put("warning", Constantes.NO_IMAGE_SELECTED);
            model.put("titulo","Registrar Usuario");
            return "admin/regUsuario";
        }
        try {
            if (!(file.getSize() > Constantes.MAX_UPLOAD_SIZE)) {
                String imagen = "";
                imagen = new UploadFiles().subirImagenFTP(file, Constantes.UPLOADS_USUARIOS);
                usuario.setRutaFoto(Constantes.URL_ENDPOINT + Constantes.UPLOADS_USUARIOS + imagen);
                usuario.setFoto(imagen);
            } else {
                model.put("error", Constantes.FILE_SIZE);
                return "admin/userProfile";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.put("error", Constantes.FILE_SIZE);
            return "admin/userProfile";
        }
        usuarioService.mergeUsuario(usuario);
        status.setComplete();
        return "redirect:/usuarios/lista";
    }

    // Ver perfil de usuario
    @GetMapping(value = "/perfil")
    public String VerPerfil(HttpServletRequest request, Map<String, Object> model, RedirectAttributes flash) {
        if (!validarSesion(request)) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }

        Usuario usuario = (Usuario) request.getSession().getAttribute("logedusuario");
        model.put("usuario", usuario);
        model.put("titulo", "Perfil de Usuario");
        return "admin/userProfile";
    }

    // Actualizar datos de usuario
    @PostMapping(value = "/actualizar")
    public String ActualizarUsuario(@Valid Usuario usuario, BindingResult result, SessionStatus status,
                                    Map<String, Object> model, HttpServletRequest request,
                                    RedirectAttributes flash, MultipartFile file) {
        if (!validarSesion(request)) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }

        if (result.hasErrors()) {
            model.put("error", Constantes.INVALID_DATA);
            model.put("titulo", "Perfil de Usuario");
            return "admin/userProfile";
        }

        if (!(file.isEmpty() || file == null)) {
            try {
                if (!(file.getSize() > Constantes.MAX_UPLOAD_SIZE)) {
                    String imagen = "";
                    try {
                        if (usuario.getFoto().isEmpty()) {
                            imagen = new UploadFiles().subirImagenFTP(file, Constantes.UPLOADS_USUARIOS);
                        } else {
                            if (new UploadFiles().eliminarImagenFTP(Constantes.UPLOADS_USUARIOS, usuario.getFoto())) {
                                imagen = new UploadFiles().subirImagenFTP(file, Constantes.UPLOADS_USUARIOS);
                            } //Agregar error si no se puede sobreescribir el fichero
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        model.put("error", e.getCause().toString() + ": " + e.getMessage());
                    }
                    usuario.setRutaFoto(Constantes.URL_ENDPOINT + Constantes.UPLOADS_USUARIOS + imagen);
                    usuario.setFoto(imagen);
                } else {
                    model.put("error", Constantes.FILE_SIZE);
                    model.put("titulo", "Perfil de Usuario");
                    return "admin/userProfile";
                }
            } catch (Exception e) {
                e.printStackTrace();
                model.put("error", Constantes.FILE_SIZE);
                model.put("titulo", "Perfil de Usuario");
                return "admin/userProfile";
            }
        }
        /*usuario.setNom_user(usuario.getNom_user());
        usuario.setApe_user(usuario.getApe_user());
        usuario.setUsuario(usuario.getUsuario());
        user.setClave(usuario.getClave());*/
        usuarioService.mergeUsuario(usuario);
        HttpSession session = request.getSession();
        session.invalidate();
        flash.addFlashAttribute("info", Constantes.PROLILE_UPDATED);
        return "redirect:/admin/login";
    }

    // Editar datos de usuario
    @GetMapping(value = "/editar/{dni}")
    public String VerUsuario(@PathVariable String dni, HttpServletRequest request, RedirectAttributes flash,
                             Map<String, Object> model) {
        if (!validarSesion(request)) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }
        Usuario usuario = usuarioService.findByDni(dni);
        model.put("usuario", usuario);
        model.put("titulo", "Editar Usuario");
        return "admin/editUsuario";
    }

    // Editar datos de usuario POST
    @PostMapping(value = "/editar")
    public String EditarUsuario(@Valid Usuario usuario, BindingResult result, SessionStatus status,
                Map<String, Object> model, RedirectAttributes flash, MultipartFile file) {
        if(result.hasErrors()){
            model.put("error",Constantes.INVALID_DATA);
            model.put("titulo", "Editar Usuario");
            return "admin/editUsuario";
        }

        if (!(file.isEmpty() || file == null)) {
            try {
                if (!(file.getSize() > Constantes.MAX_UPLOAD_SIZE)) {
                    String imagen = "";
                    try {
                        if (usuario.getFoto().isEmpty()) {
                            imagen = new UploadFiles().subirImagenFTP(file, Constantes.UPLOADS_USUARIOS);
                        } else {
                            if (new UploadFiles().eliminarImagenFTP(Constantes.UPLOADS_USUARIOS, usuario.getFoto())) {
                                imagen = new UploadFiles().subirImagenFTP(file, Constantes.UPLOADS_USUARIOS);
                            } // TODO Agregar error si no se puede sobreescribir el fichero
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        model.put("error", e.getCause().toString() + ": " + e.getMessage());
                    }
                    usuario.setRutaFoto(Constantes.URL_ENDPOINT + Constantes.UPLOADS_USUARIOS + imagen);
                    usuario.setFoto(imagen);
                } else {
                    model.put("error", Constantes.FILE_SIZE);
                    model.put("titulo", "Editar Usuario");
                    return "admin/editUsuario";
                }
            } catch (Exception e) {
                e.printStackTrace();
                model.put("error", Constantes.FILE_SIZE);
                model.put("titulo", "Editar Usuario");
                return "admin/editUsuario";
            }
        }
        /*user.setNom_user(editUser.getNom_user());
        user.setApe_user(editUser.getApe_user());
        user.setUsuario(editUser.getUsuario());
        user.setClave(editUser.getClave());*/
        usuarioService.mergeUsuario(usuario);
        status.setComplete();
        flash.addFlashAttribute("success",Constantes.CHANGES_SUCCESSFULL);
        return "redirect:/usuarios/lista";
    }

    // Deshabilitar Usuario
    @GetMapping(value = "/disabled/{dni}")
    public String DisabledUser(@PathVariable String dni,RedirectAttributes flash, HttpServletRequest request){
        if (!validarSesion(request)){
            flash.addFlashAttribute("error",Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }
        usuarioService.disabledUser(dni);
        flash.addFlashAttribute("success",Constantes.CHANGES_SUCCESSFULL);
        return "redirect:/usuarios/lista";
    }

    // Habilitar Usuario
    @GetMapping(value = "/enabled/{dni}")
    public String EnabledUser(@PathVariable String dni,RedirectAttributes flash, HttpServletRequest request){
        if (!validarSesion(request)){
            flash.addFlashAttribute("error",Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }
        usuarioService.enabledUser(dni);
        flash.addFlashAttribute("success",Constantes.CHANGES_SUCCESSFULL);
        return "redirect:/usuarios/lista";
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
