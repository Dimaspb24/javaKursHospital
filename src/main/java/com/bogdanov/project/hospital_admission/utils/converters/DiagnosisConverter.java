package com.bogdanov.project.hospital_admission.utils.converters;

import com.bogdanov.project.hospital_admission.model.Diagnosis;
import com.bogdanov.project.hospital_admission.utils.dto.DiagnosisDto;

public class DiagnosisConverter {

    public static Diagnosis toDiagnosis(DiagnosisDto diagnosisDto) {
        return new Diagnosis(
                diagnosisDto.getName()
        );
    }

    public static Diagnosis toDiagnosisWithId(DiagnosisDto diagnosisDto) {
        return new Diagnosis(
                diagnosisDto.getId(),
                diagnosisDto.getName()
        );
    }

    public static DiagnosisDto toDiagnosisDto(Diagnosis diagnosis) {
        return new DiagnosisDto(
                diagnosis.getId(),
                diagnosis.getName()
        );
    }
}
