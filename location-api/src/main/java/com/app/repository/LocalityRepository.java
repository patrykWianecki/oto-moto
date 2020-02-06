package com.app.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Locality;

@Repository
public interface LocalityRepository extends MongoRepository<Locality, String> {

  Optional<Locality> findByName(String name);
}
