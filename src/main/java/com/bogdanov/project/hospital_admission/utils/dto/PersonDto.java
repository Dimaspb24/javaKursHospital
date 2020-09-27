package com.bogdanov.project.hospital_admission.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PersonDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String patherName;
    private String diagnosisName;
    private String wardName;
}
