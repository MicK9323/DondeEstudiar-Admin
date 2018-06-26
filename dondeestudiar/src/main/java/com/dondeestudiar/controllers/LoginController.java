package com.dondeestudiar.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dondeestudiar.models.entities.Usuario;
import com.dondeestudiar.models.services.IUsuarioService;

@Controller
public class LoginController {
	
	@Autowired
	IUsuarioService usuarioService;
	
	@ModelAttribute("usuario")
	public Usuario setUsuario() {
		return new Usuario();
	}

	@GetMapping(value={"/admin/login","/index",""})
	public String login(HttpServletRequest request) {
		if( request.getSession().getAttribute("logedusuario") == null ) {
			return "login";
		}else {
			return "admin/main";
		}
		
	}
	
	@PostMapping(value="/admin/login")
	public String loged(@ModelAttribute("usuario") Usuario user, HttpSession session ,Map<String, Object> model) {
		Usuario usuario = usuarioService.login( user.getUsuario(), user.getClave());
		if( usuario == null  ) {
			model.put("error", "Error: Usuario o Contraseña incorrecta");
			return "login";
		}else {
			if( !usuario.isEstado() ) {
				model.put("error", "Error: Su usuario no está habilitado para iniciar sesión");
				return "login";
			}else {
				session.setAttribute("logedusuario", usuario);
				model.put("titulo", "Principal");
				return "admin/main";
			}
		}
	}
	
	@GetMapping(value="/admin/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/index";
	}
	
	
}
