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
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/usuarios")
@SessionAttributes(names = {"user", "newUser"})
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

    // Registrar Usuario
    @GetMapping(value = "/registrar")
    public String RegistrarUsuario(HttpServletRequest request, Map<String, Object> model,
                                   RedirectAttributes flash) {
        if (!validarSesion(request)) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }
        Usuario newUser = new Usuario();
        model.put("newUser", newUser);
        model.put("titulo", "Registrar Administrador");
        return "admin/regUsuario";
    }

    // Registrar administrador
    @PostMapping(value = "/nuevo")
    public String NuevoAdministrador(@Valid Usuario newUser, BindingResult result, RedirectAttributes flash,
                                     SessionStatus status, HttpServletRequest request, MultipartFile file, Map<String, Object> model) {
        if (!validarSesion(request)) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }

        if (result.hasErrors()) {
            model.put("error", Constantes.INVALID_DATA);
            return "admin/regUsuario";
        }

        if (file.isEmpty() || file == null) {
            model.put("warning", Constantes.NO_IMAGE_SELECTED);
            return "admin/regUsuario";
        }
        try {
            if (!(file.getSize() > Constantes.MAX_UPLOAD_SIZE)) {
                String imagen = "";
                imagen = new UploadFiles().subirImagenFTP(file, Constantes.UPLOADS_USUARIOS);
                newUser.setRutaFoto(Constantes.URL_ENDPOINT + Constantes.UPLOADS_USUARIOS + imagen);
                newUser.setFoto(imagen);
            } else {
                model.put("error", Constantes.FILE_SIZE);
                return "admin/userProfile";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.put("error", Constantes.FILE_SIZE);
            return "admin/userProfile";
        }
        status.setComplete();
        usuarioService.mergeUsuario(newUser);
        return "redirect:/usuarios/lista";
    }

    // Ver perfil de usuario
    @GetMapping(value = "/perfil")
    public String VerPerfil(HttpServletRequest request, Map<String, Object> model, RedirectAttributes flash) {
        if (!validarSesion(request)) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }

        Usuario user = (Usuario) request.getSession().getAttribute("logedusuario");
        model.put("user", user);
        model.put("titulo", "Perfil de Usuario");
        return "admin/userProfile";
    }

    // Actualizar datos de usuario
    @PostMapping(value = "/actualizar")
    public String ActualizarUsuario(@Valid Usuario usuario, BindingResult result, SessionStatus status,
                                    @SessionAttribute("user") Usuario user, Map<String, Object> model, HttpServletRequest request,
                                    RedirectAttributes flash, MultipartFile file) {
        if (!validarSesion(request)) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }

        if (result.hasErrors()) {
            model.put("error", Constantes.INVALID_DATA);
            return "admin/userProfile";
        }

        if (!(file.isEmpty() || file == null)) {
            try {
                if (!(file.getSize() > Constantes.MAX_UPLOAD_SIZE)) {
                    String imagen = "";
                    try {
                        if (user.getFoto().isEmpty()) {
                            imagen = new UploadFiles().subirImagenFTP(file, Constantes.UPLOADS_USUARIOS);
                        } else {
                            if (new UploadFiles().eliminarImagenFTP(Constantes.UPLOADS_USUARIOS, user.getFoto())) {
                                imagen = new UploadFiles().subirImagenFTP(file, Constantes.UPLOADS_USUARIOS);
                            } //Agregar error si no se puede sobreescribir el fichero
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        model.put("error", e.getCause().toString() + ": " + e.getMessage());
                    }
                    user.setRutaFoto(Constantes.URL_ENDPOINT + Constantes.UPLOADS_USUARIOS + imagen);
                    user.setFoto(imagen);
                } else {
                    model.put("error", Constantes.FILE_SIZE);
                    return "admin/userProfile";
                }
            } catch (Exception e) {
                e.printStackTrace();
                model.put("error", Constantes.FILE_SIZE);
                return "admin/userProfile";
            }
        }
        user.setNom_user(usuario.getNom_user());
        user.setApe_user(usuario.getApe_user());
        user.setUsuario(usuario.getUsuario());
        user.setClave(usuario.getClave());
        usuarioService.mergeUsuario(user);
        flash.addFlashAttribute("info", Constantes.PROLILE_UPDATED);
        status.setComplete();
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
        Usuario user = usuarioService.findByDni(dni);
        model.put("user", user);
        model.put("titulo", "Editar Usuario");
        return "admin/editUsuario";
    }

    // Editar datos de usuario POST
    @PostMapping(value = "/editar")
    public String EditarUsuario(@Valid Usuario editUser, BindingResult result, SessionStatus status,
                @SessionAttribute("user") Usuario user,Map<String, Object> model, RedirectAttributes flash, MultipartFile file) {
        if(result.hasErrors()){
            model.put("error",Constantes.INVALID_DATA);
            return "admin/editUsuario";
        }

        if (!(file.isEmpty() || file == null)) {
            try {
                if (!(file.getSize() > Constantes.MAX_UPLOAD_SIZE)) {
                    String imagen = "";
                    try {
                        if (user.getFoto().isEmpty()) {
                            imagen = new UploadFiles().subirImagenFTP(file, Constantes.UPLOADS_USUARIOS);
                        } else {
                            if (new UploadFiles().eliminarImagenFTP(Constantes.UPLOADS_USUARIOS, user.getFoto())) {
                                imagen = new UploadFiles().subirImagenFTP(file, Constantes.UPLOADS_USUARIOS);
                            } // TODO Agregar error si no se puede sobreescribir el fichero
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        model.put("error", e.getCause().toString() + ": " + e.getMessage());
                    }
                    user.setRutaFoto(Constantes.URL_ENDPOINT + Constantes.UPLOADS_USUARIOS + imagen);
                    user.setFoto(imagen);
                } else {
                    model.put("error", Constantes.FILE_SIZE);
                    return "admin/editUsuario";
                }
            } catch (Exception e) {
                e.printStackTrace();
                model.put("error", Constantes.FILE_SIZE);
                return "admin/editUsuario";
            }
        }
        user.setNom_user(editUser.getNom_user());
        user.setApe_user(editUser.getApe_user());
        user.setUsuario(editUser.getUsuario());
        user.setClave(editUser.getClave());
        usuarioService.mergeUsuario(user);
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
