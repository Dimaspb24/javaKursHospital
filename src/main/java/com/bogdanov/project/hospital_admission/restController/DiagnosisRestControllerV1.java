package com.bogdanov.project.hospital_admission.restController;

import com.bogdanov.project.hospital_admission.model.Diagnosis;
import com.bogdanov.project.hospital_admission.model.User;
import com.bogdanov.project.hospital_admission.repository.UserRepository;
import com.bogdanov.project.hospital_admission.services.api.DiagnosisService;
import com.bogdanov.project.hospital_admission.utils.dto.DiagnosisDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/diagnoses")
public class DiagnosisRestControllerV1 {

    private final UserRepository userRepository;
    private final DiagnosisService diagnosisService;

    @Autowired
    public DiagnosisRestControllerV1(UserRepository userRepository, DiagnosisService diagnosisService) {
        this.userRepository = userRepository;
        this.diagnosisService = diagnosisService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<Map<String, Object>> findAllDiagnoses() {

        Authentication auth = SecurityContextHolder.getContext ().getAuthentication();
        Optional<User> smth = userRepository.findByEmail(auth.getName());

        Set<DiagnosisDto> diagnoses = diagnosisService.findAll();
        List<DiagnosisDto> list = diagnoses.stream().collect(Collectors.toList());
        Map<String, Object> map = new HashMap<>();
        map.put("diagnoses", list);
        map.put("user", smth.get().getEmail());

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<DiagnosisDto> findDiagnosisById(@PathVariable Long id) {
        DiagnosisDto diagnosis = diagnosisService.findById(id);
        return new ResponseEntity<>(diagnosis, HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<DiagnosisDto>> findDiagnosesByName(@PathVariable String name) {
        List<DiagnosisDto> diagnoses = diagnosisService.findByName(name);
        return new ResponseEntity<>(diagnoses, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<DiagnosisDto> saveDiagnosis(@RequestBody DiagnosisDto diagnosis) {
        DiagnosisDto diagnosisDto = diagnosisService.saveDiagnosis(diagnosis);
        return new ResponseEntity<>(diagnosisDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> deleteDiagnosisById(@PathVariable Long id) {
        diagnosisService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
