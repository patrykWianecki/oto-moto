package com.app.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.County;

@Repository
public interface CountyRepository extends JpaRepository<County, Long> {

  Optional<County> findCountyByName(String countyName);
}
