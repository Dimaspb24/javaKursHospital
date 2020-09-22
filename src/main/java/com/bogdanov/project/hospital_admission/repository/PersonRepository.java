package com.bogdanov.project.hospital_admission.repository;

import com.bogdanov.project.hospital_admission.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select p from Person p where p.firstName like %?1 or p.lastName like %?1 or p.patherName like %?1")
    Optional<Set<Person>> findByLikeName(String firstName);
}

