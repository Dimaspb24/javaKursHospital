package com.bogdanov.project.hospital_admission.restController;

import com.bogdanov.project.hospital_admission.model.User;
import com.bogdanov.project.hospital_admission.repository.UserRepository;
import com.bogdanov.project.hospital_admission.security.SecurityUser;
import com.bogdanov.project.hospital_admission.utils.dto.DiagnosisDto;
import com.bogdanov.project.hospital_admission.model.Diagnosis;
import com.bogdanov.project.hospital_admission.services.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

        List<DiagnosisDto> diagnosisList = diagnosisService.findAll();
        Map<String, Object> map = new HashMap<>();
        map.put("diagnoses", diagnosisList);
        map.put("user", smth.get().getEmail());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<Diagnosis> findDiagnosisById(@PathVariable Long id) {
        Diagnosis diagnosis = diagnosisService.findById(id);
        return new ResponseEntity<>(diagnosis, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<Diagnosis> saveDiagnosis(@RequestBody Diagnosis diagnosis) {
        return new ResponseEntity<>(diagnosisService.saveDiagnosis(diagnosis), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> deleteDiagnosisById(@PathVariable Long id) {
        diagnosisService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
