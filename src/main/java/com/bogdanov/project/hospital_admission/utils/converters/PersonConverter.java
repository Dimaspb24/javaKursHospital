package com.bogdanov.project.hospital_admission.utils.converters;

import com.bogdanov.project.hospital_admission.model.Person;
import com.bogdanov.project.hospital_admission.repository.DiagnosisRepository;
import com.bogdanov.project.hospital_admission.repository.WardRepository;
import com.bogdanov.project.hospital_admission.utils.dto.PersonDto;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter {

    private static WardRepository wardRepository;
    private static DiagnosisRepository diagnosisRepository;

    public PersonConverter(WardRepository wardRepository, DiagnosisRepository diagnosisRepository) {
        PersonConverter.wardRepository = wardRepository;
        PersonConverter.diagnosisRepository = diagnosisRepository;
    }

    public static Person toPerson(PersonDto personDto) {
        Person person = new Person();
        person.setId(personDto.getId());
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setPatherName(personDto.getPatherName());
        person.setDiagnosis(diagnosisRepository.findByName(personDto.getDiagnosisName()).get());
        person.setWard(wardRepository.findByName(personDto.getWardName()).get());
        return person;
    }

    public static PersonDto toPersonDto(Person person) {
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

