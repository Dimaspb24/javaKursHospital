package com.bogdanov.project.hospital_admission.services.api;

import com.bogdanov.project.hospital_admission.utils.dto.PersonDto;

import java.util.List;

public interface PersonService {

    PersonDto findById(Long id);

    List<PersonDto> findByLikeName(String name);

    List<PersonDto> findAll();

    PersonDto saveOrUpdatePerson(PersonDto person);

    void deleteById(Long id);
}
