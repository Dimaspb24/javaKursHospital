package com.bogdanov.project.hospital_admission.services.api;


import com.bogdanov.project.hospital_admission.utils.dto.DiagnosisDto;

import java.util.List;

public interface DiagnosisService {

    DiagnosisDto findById(Long id);

    List<DiagnosisDto> findByName(String name);

    List<DiagnosisDto> findAll();

    DiagnosisDto saveDiagnosis(DiagnosisDto diagnosis);

    void deleteById(Long id);

    void deleteByName(String name);
}
