package com.mongo.SaberPro.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mongo.SaberPro.exception.NotFoundException;
import com.mongo.SaberPro.model.Estudiante;
import com.mongo.SaberPro.repository.EstudianteRepository;

@Controller
@SessionAttributes("tipo")
public class EstudianteController {

	@Autowired
	EstudianteRepository eRepository;

	@GetMapping("/estudiantes")
	public String estudiantesList(Model model) {
		List<Estudiante> estudiantes = eRepository.findAll();

		estudiantes = estudiantes.stream()
				.sorted((e1, e2) -> Integer.compare(e2.getExamen().getTotal(), e1.getExamen().getTotal()))
				.collect(Collectors.toList());
		
		model.addAttribute("estudiantes", estudiantes);
		return "estudiantesList";
	}
	
	@GetMapping("/estudiantes/vista/{estudianteId}")
	public String estudianteRanking(@PathVariable("estudianteId") String estudianteId, Model model) {
		Estudiante estudianteActual = eRepository.findById(estudianteId).orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
		
		if(!estudianteActual.getExamen().isAnulado()) {
			List<Estudiante> estudiantes = eRepository.findAll();

			estudiantes = estudiantes.stream()
					.sorted((e1, e2) -> Integer.compare(e2.getExamen().getTotal(), e1.getExamen().getTotal()))
					.collect(Collectors.toList());
			
			int ranking = 0;
		    for (int i = 0; i < estudiantes.size(); i++) {
		        if (estudiantes.get(i).getId().equals(estudianteActual.getId())) {
		            ranking = i + 1;
		            break;
		        }
		    }
			
			model.addAttribute("ranking", ranking);
		}
		
		else {
			model.addAttribute("ranking", "Anulado");
		}
		
		return "estudianteView";
	}

	@GetMapping("/estudiantes/edit/{id}")
	public String estudianteEdit(@PathVariable("id") String id, Model model) {
		model.addAttribute("estudiante",
				eRepository.findById(id).orElseThrow(() -> new NotFoundException("Estudiante no encontrado")));
		model.addAttribute("titulo", "Editar estudiante");
		model.addAttribute("tipo", "antiguo");
		return "estudiantes-form";
	}

	@GetMapping("/estudiantes/new")
	public String estudianteNew(Model model) {
		model.addAttribute("estudiante", new Estudiante());
		model.addAttribute("titulo", "Agregar estudiante");
		model.addAttribute("tipo", "nuevo");
		return "estudiantes-form";
	}

	@Transactional
	@PostMapping("/estudiantes/save")
	public String estudianteSave(@ModelAttribute("estudiantes") Estudiante estudiante,
			@ModelAttribute("tipo") String tipo) {
		if (estudiante.getId().isEmpty()) {
			estudiante.setId(null);
		}

		eRepository.save(estudiante);
		if (tipo.equals("nuevo")) {
			return "redirect:/examenes/new/" + estudiante.getId();
		} else {
			return "redirect:/examenes/edit/" + estudiante.getExamen().getnRegistro() + "/" + estudiante.getId();
		}
	}

	@GetMapping("/estudiantes/delete/{estudianteId}")
	public String estudiantesDelete(@PathVariable("estudianteId") String estudianteId) {
		eRepository.findById(estudianteId).orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
		eRepository.deleteById(estudianteId);
		return "redirect:/estudiantes";
	}
}
