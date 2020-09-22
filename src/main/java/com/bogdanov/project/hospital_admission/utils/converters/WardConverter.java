package com.bogdanov.project.hospital_admission.utils.converters;

import com.bogdanov.project.hospital_admission.model.Ward;
import com.bogdanov.project.hospital_admission.utils.dto.WardDto;

public class WardConverter {

    public static Ward fromWardDtoToWard(WardDto wardDto) {
        return new Ward(wardDto.getId(),
                wardDto.getName(),
                wardDto.getMaxCount()
        );
    }

    public static WardDto fromWardToWardDto(Ward ward) {
        return new WardDto(
                ward.getId(),
                ward.getName(),
                ward.getMaxCount()
        );
    }
}

