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
import com.mongo.SaberPro.model.Examen;
import com.mongo.SaberPro.repository.ExamenRepository;

@RestController
@RequestMapping(value = "/api/examenes")
public class ExamenRestController {

	@Autowired
	ExamenRepository repository;
	
	@PostMapping("/")
	public Examen saveExamen(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Examen examen = mapper.convertValue(body, Examen.class);
		
		String nRegistro = (String) body.get("nRegistro");
		if (nRegistro == null || nRegistro.trim().isEmpty()) {
			throw new IllegalArgumentException("El campo 'Número de registro' es obligatorio y no puede estar vacío.");
		}
		
		examen.setnRegistro(nRegistro);
		
		int comunicacion = (Integer) body.get("comunicacion");
		if (comunicacion < 0) {
			throw new IllegalArgumentException("El campo 'Comunicación escrita' debe tener un valor valido");
		}
		
		examen.setComunicacion(comunicacion);
		
		int razonamiento = (Integer) body.get("razonamiento");
		if (razonamiento < 0) {
			throw new IllegalArgumentException("El campo 'Razonmaiento cuantitativo' debe tener un valor valido");
		}
		
		examen.setRazonamiento(razonamiento);
		
		int lectura = (Integer) body.get("lectura");
		if (lectura < 0) {
			throw new IllegalArgumentException("El campo 'lectura' debe tener un valor valido");
		}
		
		examen.setLectura(lectura);
		
		int cCiudadanas = (Integer) body.get("cCiudadanas");
		if (cCiudadanas < 0) {
			throw new IllegalArgumentException("El campo 'Comepetencias ciudadanas' debe tener un valor valido");
		}
		
		examen.setcCiudadanas(cCiudadanas);
		
		int ingles = (Integer) body.get("ingles");
		if (ingles < 0) {
			throw new IllegalArgumentException("El campo 'Ingles' debe tener un valor valido");
		}
		
		examen.setIngles(ingles);
		
		int fProyectos = (Integer) body.get("fProyectos");
		if (fProyectos < 0) {
			throw new IllegalArgumentException("El campo 'Formulación de royectos' debe tener un valor valido");
		}
		
		examen.setfProyectos(fProyectos);
		
		int pCientifico = (Integer) body.get("pCientifico");
		if (pCientifico < 0) {
			throw new IllegalArgumentException("El campo 'Pensamiento científico' debe tener un valor valido");
		}
		
		examen.setpCientifico(pCientifico);
		
		int dSoftware = (Integer) body.get("dSoftware");
		if (dSoftware < 0) {
			throw new IllegalArgumentException("El campo 'Diseño de software' debe tener un valor valido");
		}
		
		examen.setdSoftware(dSoftware);
		
		String nIngles = (String) body.get("nIngles");
		if (nIngles == null || nIngles.trim().isEmpty()) {
			throw new IllegalArgumentException("El campo 'Nivel del ingles' es obligatorio y no puede estar vacío.");
		}
		
		examen.setnIngles(nIngles);
		
		boolean anulado = (boolean) body.get("anulado");
		
		examen.setAnulado(anulado);
		
		return repository.save(examen);
	}
	
	
	@PutMapping("/{id}")
	public Examen updateExamen(@PathVariable("nRegistro") String nRegistro, @RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Examen examen = mapper.convertValue(body, Examen.class);
		examen.setnRegistro(nRegistro);
		return examen;
	}
	
	@DeleteMapping("/{id}")
	public Examen deleteExamen(@PathVariable("nRegistro") String nRegistro) {
		Examen examen = repository.findById(nRegistro).orElseThrow(() -> new NotFoundException("Examen no encontrado"));
		repository.deleteById(nRegistro);
		return examen;
	}
}
