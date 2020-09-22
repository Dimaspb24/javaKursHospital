package com.bogdanov.project.hospital_admission.services.api;


import com.bogdanov.project.hospital_admission.model.Diagnosis;
import com.bogdanov.project.hospital_admission.utils.dto.DiagnosisDto;

import java.util.List;
import java.util.Set;

public interface DiagnosisService {

    DiagnosisDto findById(Long id);

    List<DiagnosisDto> findByName(String name);

    Set<DiagnosisDto> findAll();

    DiagnosisDto saveDiagnosis(DiagnosisDto diagnosis);

    void deleteById(Long id);
}
