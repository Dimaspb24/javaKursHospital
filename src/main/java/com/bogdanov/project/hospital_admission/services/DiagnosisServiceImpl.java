package com.bogdanov.project.hospital_admission.services;

import com.bogdanov.project.hospital_admission.utils.converters.DiagnosisConverter;
import com.bogdanov.project.hospital_admission.utils.dto.DiagnosisDto;
import com.bogdanov.project.hospital_admission.model.Diagnosis;
import com.bogdanov.project.hospital_admission.repository.DiagnosisRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service
public class DiagnosisServiceImpl implements DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;

    public DiagnosisServiceImpl(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    @Override
    public Diagnosis findById(Long id) {
        return diagnosisRepository.getOne(id);
    }

    @Override
    public List<Diagnosis> findByName(String name) {
        return diagnosisRepository.findByName(name).orElseThrow(
                () -> new NoSuchElementException("There is no diagnosis with " + name + " name"));
    }

    @Override
    public List<DiagnosisDto> findAll() {
        return diagnosisRepository.findAll()
                .stream()
                .map(DiagnosisConverter::fromDiagnosisToDiagnosisDto)
                .collect(Collectors.toList());
    }

    @Override
    public Diagnosis saveDiagnosis(Diagnosis diagnosis) {
        return diagnosisRepository.save(diagnosis);
    }

    @Override
    public void deleteById(Long id) {
        diagnosisRepository.deleteById(id);
    }
}
