package com.app.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Voivodeship;

@Repository
public interface VoivodeshipRepository extends MongoRepository<Voivodeship, String> {

  Optional<Voivodeship> findByName(String name);
}
