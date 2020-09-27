package com.bogdanov.project.hospital_admission.repository;

import com.bogdanov.project.hospital_admission.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {
    Optional<Ward> findByName(String name);

    Optional<List<Ward>> findByNameContaining(String name);
}
