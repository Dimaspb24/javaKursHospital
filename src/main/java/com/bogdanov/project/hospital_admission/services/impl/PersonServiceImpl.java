package com.bogdanov.project.hospital_admission.services.impl;


import com.bogdanov.project.hospital_admission.model.Person;
import com.bogdanov.project.hospital_admission.repository.PersonRepository;
import com.bogdanov.project.hospital_admission.services.api.PersonService;
import com.bogdanov.project.hospital_admission.utils.converters.PersonConverter;
import com.bogdanov.project.hospital_admission.utils.dto.PersonDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public PersonDto findById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        person.orElseThrow(() -> new NoSuchElementException("There is no person"));
        return PersonConverter.fromPersonToPersonDto(person.get());
    }

    @Override
    public Set<PersonDto> findByLikeName(String name) {
        Optional<Set<Person>> person = personRepository.findByLikeName(name);
        person.orElseThrow(() -> new NoSuchElementException("There is no person with name"));
        return person
                .get()
                .stream()
                .map(PersonConverter::fromPersonToPersonDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<PersonDto> findAll() {
        return personRepository
                .findAll()
                .stream()
                .map(PersonConverter::fromPersonToPersonDto)
                .collect(Collectors.toSet());
    }

    @Override
    public PersonDto savePerson(PersonDto person) {
        Person thatPerson = personRepository.save(PersonConverter.fromPersonDtoToPerson(person));
        return PersonConverter.fromPersonToPersonDto(thatPerson);
    }

    @Override
    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }
}
