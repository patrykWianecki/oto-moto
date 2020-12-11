package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Locality;

@Repository
public interface LocalityRepository extends JpaRepository<Locality, Long> {

  Optional<Locality> findLocalityByName(String name);
}
