package com.bogdanov.project.hospital_admission.utils.dto;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WardDto {
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private Integer maxCount;
//    private Set<PersonDto> persons;

}
