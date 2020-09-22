package com.bogdanov.project.hospital_admission.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class WardDto {
    private Long id;
    private String name;
    private Integer maxCount;
//    private Set<PersonDto> persons;
}
