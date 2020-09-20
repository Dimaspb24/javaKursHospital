package com.bogdanov.project.hospital_admission.restController;

import com.bogdanov.project.hospital_admission.utils.dto.DiagnosisDto;
import com.bogdanov.project.hospital_admission.model.Diagnosis;
import com.bogdanov.project.hospital_admission.services.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/diagnoses")
public class DiagnosisRestControllerV1 {

    private final DiagnosisService diagnosisService;

    @Autowired
    public DiagnosisRestControllerV1(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<DiagnosisDto>> findAllDiagnoses() {
        List<DiagnosisDto> diagnosisList = diagnosisService.findAll();
        return new ResponseEntity<>(diagnosisList, HttpStatus.OK);
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
