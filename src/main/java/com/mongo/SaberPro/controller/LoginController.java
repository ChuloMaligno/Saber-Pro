package com.mongo.SaberPro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mongo.SaberPro.repository.CoordinadorRepository;
import com.mongo.SaberPro.repository.EstudianteRepository;
import com.mongo.SaberPro.model.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	EstudianteRepository eRepository;
	@Autowired
	CoordinadorRepository cRepository;
	
	@GetMapping("/login/{tipo}")
	public String login(@PathVariable("tipo") String tipo, Model model) {
		model.addAttribute("tipo", tipo);
		return "login";
	}
	
	
	@PostMapping("/login/estudiante")
	public String loginEstudiante(@RequestParam("usuario") String usuario, @RequestParam("contraseña")
	String contraseña, HttpSession session, Model model) {
		Estudiante estudiante = eRepository.findByUsuario(usuario);
		
		if(estudiante != null && estudiante.getContraseña().equals(contraseña)) {
			session.setAttribute("usuario", estudiante);
			return "redirect:/estudiantes/vista/" + estudiante.getId();
		}
		else {
			model.addAttribute("error", "Usuario o contraseña invalidos");
			return "login";
		}
	}
	
	
	@PostMapping("/login/coordinador")
	public String loginCoordinador(@RequestParam("usuario") String usuario, @RequestParam("contraseña")
	String contraseña, HttpSession session, Model model) {
		Coordinador coordinador = cRepository.findByUsuario(usuario);
		
		if(coordinador != null && coordinador.getContraseña().equals(contraseña)) {
			session.setAttribute("usuario", coordinador);
			return "redirect:/estudiantes";
		}
		else {
			model.addAttribute("error", "Usuario o contraseña invalidos");
			return "login";
		}
	}
	
	@GetMapping("/login/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
