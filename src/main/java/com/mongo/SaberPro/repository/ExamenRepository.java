package com.mongo.SaberPro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongo.SaberPro.model.Examen;

@Repository
public interface ExamenRepository extends MongoRepository<Examen, String>{

}
