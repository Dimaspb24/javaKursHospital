package com.bogdanov.project.hospital_admission.utils.converters;

import com.bogdanov.project.hospital_admission.utils.dto.DiagnosisDto;
import com.bogdanov.project.hospital_admission.model.Diagnosis;

public class DiagnosisConverter {

    public static Diagnosis fromDiagnosisDtoToDiagnosis(DiagnosisDto diagnosisDto) {
        return new Diagnosis(
                diagnosisDto.getId(),
                diagnosisDto.getName()
        );
    }

    public static DiagnosisDto fromDiagnosisToDiagnosisDto(Diagnosis diagnosis) {
        return new DiagnosisDto(
                diagnosis.getId(),
                diagnosis.getName()
        );
    }
}
