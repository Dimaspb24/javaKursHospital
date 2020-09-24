package com.bogdanov.project.hospital_admission.services.impl;

import com.bogdanov.project.hospital_admission.model.Diagnosis;
import com.bogdanov.project.hospital_admission.repository.DiagnosisRepository;
import com.bogdanov.project.hospital_admission.services.api.DiagnosisService;
import com.bogdanov.project.hospital_admission.utils.converters.DiagnosisConverter;
import com.bogdanov.project.hospital_admission.utils.dto.DiagnosisDto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class DiagnosisServiceImpl implements DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;

    public DiagnosisServiceImpl(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    @Override
    public DiagnosisDto findById(Long id) {
        Diagnosis diagnosis = diagnosisRepository.getOne(id);
        return DiagnosisConverter.toDiagnosisDto(diagnosis);
    }

    @Override
    public List<DiagnosisDto> findByName(String name) {
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
    public DiagnosisDto saveDiagnosis(DiagnosisDto diagnosis) {
        Optional<Diagnosis> foundDiagnosis = diagnosisRepository.findByName(diagnosis.getName());
        if (foundDiagnosis.isEmpty()) {
            Diagnosis diagnosisToSave = DiagnosisConverter.toDiagnosis(diagnosis);
            Diagnosis thatDiagnosis = diagnosisRepository.save(diagnosisToSave);
            return DiagnosisConverter.toDiagnosisDto(thatDiagnosis);
        } else {
            Diagnosis changedDiagnosis = foundDiagnosis.get();
            changedDiagnosis.setName(diagnosis.getName());
            return DiagnosisConverter.toDiagnosisDto(changedDiagnosis);
        }
    }

    @Override
    public void deleteById(Long id) {
        diagnosisRepository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        Optional<Diagnosis> diagnosis = diagnosisRepository.findByName(name);
        if (!diagnosis.isEmpty()) {
            diagnosisRepository.delete(diagnosis.get());
        }
    }
}
