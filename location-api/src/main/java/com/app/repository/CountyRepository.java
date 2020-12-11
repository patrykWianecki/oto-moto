package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.County;

@Repository
public interface CountyRepository extends JpaRepository<County, String> {

  Optional<County> findCountyByName(String name);
}
