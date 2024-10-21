package com.mongo.SaberPro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongo.SaberPro.model.Coordinador;

@Repository
public interface CoordinadorRepository extends MongoRepository<Coordinador, String>{
	
	public Coordinador findByUsuario(String usuario);

}
