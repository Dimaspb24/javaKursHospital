package com.bogdanov.project.hospital_admission.services;

import com.bogdanov.project.hospital_admission.utils.dto.DiagnosisDto;
import com.bogdanov.project.hospital_admission.model.Diagnosis;

import java.util.List;

public interface DiagnosisService {

    Diagnosis findById(Long id);

    List<Diagnosis> findByName(String name);

    List<DiagnosisDto> findAll();

    Diagnosis saveDiagnosis(Diagnosis diagnosis);

    void deleteById(Long id);
}
