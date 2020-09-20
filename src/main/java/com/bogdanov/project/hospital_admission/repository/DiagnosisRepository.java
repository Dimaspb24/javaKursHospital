package com.bogdanov.project.hospital_admission.repository;

import com.bogdanov.project.hospital_admission.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    Optional<List<Diagnosis>> findByName(String name);
}
