package com.bogdanov.project.hospital_admission.services.impl;

import com.bogdanov.project.hospital_admission.model.Diagnosis;
import com.bogdanov.project.hospital_admission.model.Person;
import com.bogdanov.project.hospital_admission.repository.DiagnosisRepository;
import com.bogdanov.project.hospital_admission.repository.PersonRepository;
import com.bogdanov.project.hospital_admission.services.api.DiagnosisService;
import com.bogdanov.project.hospital_admission.utils.converters.DiagnosisConverter;
import com.bogdanov.project.hospital_admission.utils.converters.PersonConverter;
import com.bogdanov.project.hospital_admission.utils.dto.DiagnosisDto;
import com.bogdanov.project.hospital_admission.utils.dto.PersonDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DiagnosisServiceImpl implements DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final PersonRepository personRepository;

    public DiagnosisServiceImpl(DiagnosisRepository diagnosisRepository, PersonRepository personRepository) {
        this.diagnosisRepository = diagnosisRepository;
        this.personRepository = personRepository;
    }

    @Override
    public DiagnosisDto findById(Long id) {
        Diagnosis diagnosis = diagnosisRepository.getOne(id);
        return DiagnosisConverter.toDiagnosisDto(diagnosis);
    }

    @Override
    public DiagnosisDto findByName(String name) {
        Optional<Diagnosis> diagnosis = diagnosisRepository.findByName(name);
        diagnosis.orElseThrow(() -> new NoSuchElementException("There is no ward"));
        return DiagnosisConverter.toDiagnosisDto(diagnosis.get());
    }

    @Override
    public List<DiagnosisDto> findByNameContaining(String name) {
        Optional<List<Diagnosis>> diagnosis = diagnosisRepository.findByNameContaining(name);
//        diagnosis.orElseThrow(() -> new NoSuchElementException("There is no diagnosis with " + name + " name"));

        if (diagnosis.isEmpty()) {
            return new ArrayList<>();
        }

        return (diagnosis.get()
                .stream()
                .map(DiagnosisConverter::toDiagnosisDto)
                .collect(Collectors.toList()));
    }

    @Override
    public List<DiagnosisDto> findAll() {
        return diagnosisRepository.findAll()
                .stream()
                .map(DiagnosisConverter::toDiagnosisDto)
                .collect(Collectors.toList());
    }

    @Override
    public DiagnosisDto saveOrUpdateDiagnosis(DiagnosisDto diagnosis) {
        if (diagnosis.getId() == null) {
            Diagnosis diagnosisToSave = DiagnosisConverter.toDiagnosis(diagnosis);
            Diagnosis thatDiagnosis = diagnosisRepository.save(diagnosisToSave);
            return DiagnosisConverter.toDiagnosisDto(thatDiagnosis);
        }
        Optional<Diagnosis> foundDiagnosis = diagnosisRepository.findById(diagnosis.getId());
        if (foundDiagnosis.isPresent()) {
            Diagnosis changedDiagnosis = foundDiagnosis.get();
            changedDiagnosis.setName(diagnosis.getName());
            diagnosisRepository.save(changedDiagnosis);
            return DiagnosisConverter.toDiagnosisDto(changedDiagnosis);
        }
        throw new IllegalArgumentException("Such diagnosis with this id noo exists");
    }

    @Override
    public List<PersonDto> deleteById(Long id) {
        Optional<List<Person>> personsWithThisWard = personRepository
                .findByDiagnosisEquals(diagnosisRepository.getOne(id));
        if (personsWithThisWard.isPresent()) {
            return personsWithThisWard.get()
                    .stream()
                    .map(PersonConverter::toPersonDto)
                    .collect(Collectors.toList());
        } else {
            diagnosisRepository.deleteById(id);
            return null;
        }

    }

    @Override
    public void deleteByName(String name) {
        Optional<Diagnosis> diagnosis = diagnosisRepository.findByName(name);
        if (!diagnosis.isEmpty()) {
            diagnosisRepository.delete(diagnosis.get());
        }
    }
}
