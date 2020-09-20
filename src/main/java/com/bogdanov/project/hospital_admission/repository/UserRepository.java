package com.bogdanov.project.hospital_admission.repository;

import com.bogdanov.project.hospital_admission.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
