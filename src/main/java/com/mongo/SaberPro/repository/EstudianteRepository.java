package com.mongo.SaberPro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongo.SaberPro.model.Estudiante;

@Repository
public interface EstudianteRepository extends MongoRepository<Estudiante, String>{

	public Estudiante findByUsuario(String usuario);
}
