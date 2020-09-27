package com.bogdanov.project.hospital_admission.services.impl;


import com.bogdanov.project.hospital_admission.model.Diagnosis;
import com.bogdanov.project.hospital_admission.model.Person;
import com.bogdanov.project.hospital_admission.model.Ward;
import com.bogdanov.project.hospital_admission.repository.DiagnosisRepository;
import com.bogdanov.project.hospital_admission.repository.PersonRepository;
import com.bogdanov.project.hospital_admission.repository.WardRepository;
import com.bogdanov.project.hospital_admission.services.api.PersonService;
import com.bogdanov.project.hospital_admission.utils.converters.PersonConverter;
import com.bogdanov.project.hospital_admission.utils.dto.PersonDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final WardRepository wardRepository;

    public PersonServiceImpl(PersonRepository personRepository, DiagnosisRepository diagnosisRepository, WardRepository wardRepository) {
        this.personRepository = personRepository;
        this.diagnosisRepository = diagnosisRepository;
        this.wardRepository = wardRepository;
    }

    @Override
    public PersonDto findById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        person.orElseThrow(() -> new NoSuchElementException("There is no person"));
        return PersonConverter.toPersonDto(person.get());
    }

    @Override
    public List<PersonDto> findByLikeName(String name) {
        Optional<List<Person>> persons = personRepository.findByLikeName(name);
//        person.orElseThrow(() -> new NoSuchElementException("There is no person with name"));

        if (persons.isEmpty()) {
            return new ArrayList<>();
        }

        return persons.get()
                .stream()
                .map(PersonConverter::toPersonDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonDto> findAll() {
        return personRepository
                .findAll()
                .stream()
                .map(PersonConverter::toPersonDto)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto saveOrUpdatePerson(PersonDto person) {
        if (person.getId() == null) {
            Person personToSave = PersonConverter.toPerson(person);
            return PersonConverter.toPersonDto(personRepository.save(personToSave));
        }
        Optional<Person> foundPerson = personRepository.findById(person.getId());
//                person.getFirstName(), person.getLastName(), person.getPatherName());

        if (foundPerson.isPresent()) {
            Person changedPerson = foundPerson.get();

            changedPerson.setFirstName(person.getFirstName());
            changedPerson.setLastName(person.getLastName());
            changedPerson.setPatherName(person.getPatherName());

            Optional<Diagnosis> diagnosis = diagnosisRepository.findByName(person.getDiagnosisName());
            diagnosis.ifPresent(changedPerson::setDiagnosis);
            diagnosis.orElseThrow(() -> new NoSuchElementException(
                    "There is no diagnosis with name:" + person.getDiagnosisName()));

            Optional<Ward> ward = wardRepository.findByName(person.getWardName());
            ward.ifPresent(changedPerson::setWard);
            ward.orElseThrow(() -> new NoSuchElementException(
                    "There is no ward with name:" + person.getWardName()));

            personRepository.save(changedPerson);
            return PersonConverter.toPersonDto(changedPerson);
        }
        throw new IllegalArgumentException("Such person with this id noo exists");
    }

    @Override
    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }
}
