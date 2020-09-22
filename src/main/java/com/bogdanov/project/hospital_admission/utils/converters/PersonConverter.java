package com.bogdanov.project.hospital_admission.utils.converters;

import com.bogdanov.project.hospital_admission.model.Person;
import com.bogdanov.project.hospital_admission.services.api.DiagnosisService;
import com.bogdanov.project.hospital_admission.services.api.WardService;
import com.bogdanov.project.hospital_admission.utils.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonConverter {

    @Autowired
    private static WardService wardService;
    @Autowired
    private static DiagnosisService diagnosisService;

    public static Person fromPersonDtoToPerson(PersonDto personDto) {
        return new Person(
                personDto.getId(),
                personDto.getFirstName(),
                personDto.getLastName(),
                personDto.getPatherName(),
                DiagnosisConverter.toDiagnosis(
                        diagnosisService.findByName(personDto.getDiagnosisName()).get(0)),
                WardConverter.fromWardDtoToWard(
                        wardService.findByName(personDto.getWardName()))
        );
    }

    public static PersonDto fromPersonToPersonDto(Person person) {
        return PersonDto.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .patherName(person.getPatherName())
                .diagnosisName(person.getDiagnosis().getName())
                .wardName(person.getWard().getName())
                .build();
    }
}

