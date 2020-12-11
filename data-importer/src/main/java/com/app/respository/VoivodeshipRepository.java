package com.app.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Voivodeship;

@Repository
public interface VoivodeshipRepository extends JpaRepository<Voivodeship, Long> {

  Optional<Voivodeship> findVoivodeshipByName(String name);
}
