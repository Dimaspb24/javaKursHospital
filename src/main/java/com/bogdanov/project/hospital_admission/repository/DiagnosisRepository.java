package com.bogdanov.project.hospital_admission.repository;

import com.bogdanov.project.hospital_admission.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
//    @Query("select d from Diagnosis d where d.name like %?1")
    Optional<List<Diagnosis>> findByNameContaining(String name);
    Optional<Diagnosis> findByName(String name);
}
