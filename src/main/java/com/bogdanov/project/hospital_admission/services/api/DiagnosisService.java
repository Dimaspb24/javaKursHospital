package com.bogdanov.project.hospital_admission.services.api;


import com.bogdanov.project.hospital_admission.utils.dto.DiagnosisDto;
import com.bogdanov.project.hospital_admission.utils.dto.PersonDto;

import java.util.List;

public interface DiagnosisService {

    DiagnosisDto findById(Long id);

    DiagnosisDto findByName(String name);

    List<DiagnosisDto> findByNameContaining(String name);

    List<DiagnosisDto> findAll();

    DiagnosisDto saveOrUpdateDiagnosis(DiagnosisDto diagnosis);

    List<PersonDto> deleteById(Long id);

    void deleteByName(String name);
}
