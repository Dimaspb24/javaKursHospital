package com.bogdanov.project.hospital_admission.services.impl;

import com.bogdanov.project.hospital_admission.model.Ward;
import com.bogdanov.project.hospital_admission.repository.WardRepository;
import com.bogdanov.project.hospital_admission.services.api.WardService;
import com.bogdanov.project.hospital_admission.utils.converters.WardConverter;
import com.bogdanov.project.hospital_admission.utils.dto.WardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WardServiceImpl implements WardService {

    private final WardRepository wardRepository;

    @Autowired
    public WardServiceImpl(WardRepository wardRepository) {
        this.wardRepository = wardRepository;
    }

    @Override
    public WardDto findById(Long id) {
        Optional<Ward> ward = wardRepository.findById(id);
        ward.orElseThrow(() -> new NoSuchElementException("There is no ward"));
        return WardConverter.fromWardToWardDto(ward.get());
    }

    @Override
    public WardDto findByName(String name) {
        Optional<Ward> ward = wardRepository.findByName(name);
        ward.orElseThrow(() -> new NoSuchElementException("There is no ward with " + name + " name"));
        return WardConverter.fromWardToWardDto(ward.get());
    }

    @Override
    public Set<WardDto> findAll() {
        return wardRepository.findAll()
                .stream()
                .map(WardConverter::fromWardToWardDto)
                .collect(Collectors.toSet());
    }

    @Override
    public WardDto saveWard(WardDto ward) {
        Ward thatWard = wardRepository.save(WardConverter.fromWardDtoToWard(ward));
        return WardConverter.fromWardToWardDto(thatWard);
    }

    @Override
    public void deleteById(Long id) {
        wardRepository.deleteById(id);
    }
}
