package com.mongo.SaberPro.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongo.SaberPro.exception.NotFoundException;
import com.mongo.SaberPro.model.Estudiante;
import com.mongo.SaberPro.model.Examen;
import com.mongo.SaberPro.repository.EstudianteRepository;
import com.mongo.SaberPro.repository.ExamenRepository;

@RestController
@RequestMapping(value="/api/estudiantes")
public class EstudianteRestController {

	@Autowired
	EstudianteRepository repository;
	@Autowired
	ExamenRepository eRepository;
	
	@PostMapping("/")
	public Estudiante saveEstudiante(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Estudiante estudiante = mapper.convertValue(body, Estudiante.class);
		
		String apellido = (String) body.get("apellido");
		if(apellido == null || apellido.trim().isEmpty()) {
			throw new IllegalArgumentException("El campo 'apellido' es obligatorio y no puede estar vacío");
		}
		
		String usuario = (String) body.get("usuario");
		if(usuario == null || usuario.trim().isEmpty()) {
			throw new IllegalArgumentException("El campo 'usuario' es obligatorio y no puede estar vacío");
		}
		
		String contraseña = (String) body.get("contraseña");
		if(contraseña == null || contraseña.trim().isEmpty()) {
			throw new IllegalArgumentException("El campo 'contraseña' es obligatorio y no puede estar vacío");
		}
		
		Object examenObject = body.get("examen");
		
		if(examenObject instanceof String) {
			String examenNregistro = (String) examenObject;
			Examen examen = eRepository.findById(examenNregistro).orElseThrow(() -> new NotFoundException("Examen no encontrado"));
			estudiante.setExamen(examen);
		}
		else {
			throw new IllegalArgumentException("La propiedad 'examen' debe ser una cadena");
		}
		
		return repository.save(estudiante);
	}
	
	@PutMapping("/{id}")
	public Estudiante updateEstudiante(@PathVariable String id, @RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Estudiante estudiante = mapper.convertValue(body, Estudiante.class);
		estudiante.setId(id);
		return repository.save(estudiante);
	}
	
	@DeleteMapping("/{id}")
	public Estudiante deleteEstudiante(@PathVariable String id) {
		Estudiante estudiante = repository.findById(id).orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
		repository.deleteById(id);
		return estudiante;
	}
}