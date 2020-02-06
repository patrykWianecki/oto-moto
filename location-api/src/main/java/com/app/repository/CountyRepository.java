package com.app.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.model.County;

@Repository
public interface CountyRepository extends MongoRepository<County, String> {

  Optional<County> findByName(String name);
}
