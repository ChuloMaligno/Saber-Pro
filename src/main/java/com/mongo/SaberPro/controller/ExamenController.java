package com.mongo.SaberPro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mongo.SaberPro.exception.NotFoundException;
import com.mongo.SaberPro.model.Estudiante;
import com.mongo.SaberPro.model.Examen;
import com.mongo.SaberPro.repository.EstudianteRepository;
import com.mongo.SaberPro.repository.ExamenRepository;

@Controller
public class ExamenController {

	@Autowired
	ExamenRepository repository;
	@Autowired
	EstudianteRepository eRepository;
	
	@GetMapping("/examenes/edit/{nRegistro}/{estudianteId}")
	public String examenesEdit(@PathVariable("nRegistro") String nRegistro, @PathVariable String estudianteId, Model model) {
		model.addAttribute("examen", repository.findById(nRegistro).orElseThrow(() -> new NotFoundException("examen no encontrado")));
		model.addAttribute("estudiante", estudianteId);
		model.addAttribute("titulo", "Editar examen");
		model.addAttribute("tipo", "antiguo");
		return "examenes-form";
	}
	
	@GetMapping("/examenes/new/{estudianteId}")
	public String examenesNew(@PathVariable String estudianteId, Model model) {
		model.addAttribute("estudiante", estudianteId);
		model.addAttribute("examen", new Examen());
		model.addAttribute("titulo", "Agregar examen");
		model.addAttribute("tipo", "nuevo");
		return "examenes-form";
	}
	
	@PostMapping("/examenes/save/{id_estudiante}")
	public String examenesSave(@ModelAttribute("examenes") Examen examen, @PathVariable String id_estudiante) {
		if(examen.getnRegistro().isEmpty()) {
			examen.setnRegistro(null);
		}
		repository.save(examen);
		Estudiante estudiante = eRepository.findById(id_estudiante).orElseThrow(() -> new NotFoundException("estudiante no encontrado"));
		estudiante.setExamen(examen);
		eRepository.save(estudiante);
		return "redirect:/estudiantes";
	}
	
	@GetMapping("/examenes/delete/{nRegistro}/{estudianteId}")
	public String examenesDelete(@PathVariable("nRegistro") String nRegistro, @PathVariable("estudianteId") String estudianteId) {
		eRepository.findById(estudianteId).orElseThrow(() -> new NotFoundException("estudiante no encontrado"));
		repository.findById(nRegistro).orElseThrow(() -> new NotFoundException("examen no encontrado"));
		repository.deleteById(nRegistro);
		eRepository.deleteById(estudianteId);
		return "redirect:/estudiantes";
	}
	
	@PostMapping("/examenes/anular/{id-estudiante}")
	public String anularExamen(@ModelAttribute("examenes") Examen examen, @PathVariable String id_estudiante) {
		if(examen.getnRegistro().isEmpty()) {
			examen.setnRegistro(null);
		}
		examen.setComunicacion(0);
		examen.setLectura(0);
		examen.setcCiudadanas(0);
		examen.setnIngles("");
		examen.setIngles(0);
		examen.setRazonamiento(0);
		examen.setdSoftware(0);
		examen.setpCientifico(0);
		examen.setfProyectos(0);
		
		repository.save(examen);
		Estudiante estudiante = eRepository.findById(id_estudiante).orElseThrow(() -> new NotFoundException("estudiante no encontrado"));
		estudiante.setExamen(examen);
		eRepository.save(estudiante);
		return "redirect:/";
	}
}
