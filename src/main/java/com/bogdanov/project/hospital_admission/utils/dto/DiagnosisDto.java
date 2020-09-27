package com.bogdanov.project.hospital_admission.utils.dto;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DiagnosisDto {
    private Long id;
    @NonNull
    private String name;
//    private Set<PersonDto> persons;
}
