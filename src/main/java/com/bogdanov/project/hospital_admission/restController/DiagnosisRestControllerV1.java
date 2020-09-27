package com.bogdanov.project.hospital_admission.restController;

import com.bogdanov.project.hospital_admission.services.api.DiagnosisService;
import com.bogdanov.project.hospital_admission.utils.dto.DiagnosisDto;
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
        List<DiagnosisDto> diagnoses = diagnosisService.findAll();
        return new ResponseEntity<>(diagnoses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<DiagnosisDto> findByIdDiagnosis(@PathVariable Long id) {
        DiagnosisDto diagnosis = diagnosisService.findById(id);
        return new ResponseEntity<>(diagnosis, HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<DiagnosisDto>> findByNameDiagnoses(@PathVariable String name) {
        List<DiagnosisDto> diagnoses = diagnosisService.findByNameContaining(name);
        return new ResponseEntity<>(diagnoses, HttpStatus.OK);
    }


    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<DiagnosisDto> saveDiagnosis(@RequestBody DiagnosisDto diagnosis) {
        return new ResponseEntity<>(diagnosisService.saveOrUpdateDiagnosis(diagnosis), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> deleteByIdDiagnosis(@PathVariable Long id) {
        diagnosisService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> deleteByIdDiagnosis(@PathVariable String name) {
        diagnosisService.deleteByName(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
