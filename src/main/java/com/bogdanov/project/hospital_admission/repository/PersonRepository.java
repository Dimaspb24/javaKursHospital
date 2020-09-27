package com.bogdanov.project.hospital_admission.repository;

import com.bogdanov.project.hospital_admission.model.Diagnosis;
import com.bogdanov.project.hospital_admission.model.Person;
import com.bogdanov.project.hospital_admission.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select p from Person p where p.firstName like %?1 or p.lastName like %?1 or p.patherName like %?1")
    Optional<List<Person>> findByLikeName(String name);

    Optional<List<Person>> findByWardEquals(Ward ward);
    Optional<List<Person>> findByDiagnosisEquals(Diagnosis ward);
}

