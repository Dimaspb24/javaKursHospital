package com.bogdanov.project.hospital_admission.utils.converters;

import com.bogdanov.project.hospital_admission.model.Ward;
import com.bogdanov.project.hospital_admission.utils.dto.WardDto;

public class WardConverter {

    public static Ward toWard(WardDto wardDto) {
        return new Ward(
                wardDto.getName(),
                wardDto.getMaxCount()
        );
    }
//
//    public static Ward toWard(WardDto wardDto) {
//        return new Ward(
//                wardDto.getId(),
//                wardDto.getName(),
//                wardDto.getMaxCount(),
//                wardDto.getPersons().stream().map()
//        );
//    }

    public static WardDto toWardDto(Ward ward) {
        return new WardDto(
                ward.getId(),
                ward.getName(),
                ward.getMaxCount()
        );

        //                , ward.getPersons().stream().map(PersonConverter::toPersonDto).collect(Collectors.toSet())
    }
}

