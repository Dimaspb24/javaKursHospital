package com.bogdanov.project.hospital_admission.services.api;

import com.bogdanov.project.hospital_admission.model.Person;
import com.bogdanov.project.hospital_admission.utils.dto.PersonDto;

import java.util.Set;

public interface PersonService {

    PersonDto findById(Long id);

    Set<PersonDto> findByLikeName(String name);

    Set<PersonDto> findAll();

    PersonDto savePerson(PersonDto person);

    void deleteById(Long id);
}
