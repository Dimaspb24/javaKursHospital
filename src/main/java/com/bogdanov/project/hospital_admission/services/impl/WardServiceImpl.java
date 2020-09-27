package com.bogdanov.project.hospital_admission.services.impl;

import com.bogdanov.project.hospital_admission.model.Ward;
import com.bogdanov.project.hospital_admission.repository.WardRepository;
import com.bogdanov.project.hospital_admission.services.api.WardService;
import com.bogdanov.project.hospital_admission.utils.converters.WardConverter;
import com.bogdanov.project.hospital_admission.utils.dto.WardDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WardServiceImpl implements WardService {

    private final WardRepository wardRepository;

    public WardServiceImpl(WardRepository wardRepository) {
        this.wardRepository = wardRepository;
    }

    @Override
    public WardDto findById(Long id) {
        Optional<Ward> ward = wardRepository.findById(id);
        ward.orElseThrow(() -> new NoSuchElementException("There is no ward"));
        return WardConverter.toWardDto(ward.get());
    }

    @Override
    public WardDto findByName(String name) {
        Optional<Ward> ward = wardRepository.findByName(name);
        ward.orElseThrow(() -> new NoSuchElementException("There is no ward"));
        return WardConverter.toWardDto(ward.get());
    }

    @Override
    public List<WardDto> findByNameContaining(String name) {
        Optional<List<Ward>> wards = wardRepository.findByNameContaining(name);
//        wards.orElseThrow(() -> new NoSuchElementException("There is no ward with " + name + " name"));

        if (wards.isEmpty()) {
            return new ArrayList<>();
        }

        return wards.get()
                .stream()
                .map(WardConverter::toWardDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<WardDto> findAll() {
        return wardRepository.findAll()
                .stream()
                .map(WardConverter::toWardDto)
                .collect(Collectors.toList());
    }

    @Override
    public WardDto saveOrUpdate(WardDto ward) {
        if (ward.getId() == null) {
            Ward wardToSave = WardConverter.toWard(ward);
            Ward thatWard = wardRepository.save(wardToSave);
            return WardConverter.toWardDto(thatWard);
        }
        Optional<Ward> foundWard = wardRepository.findById(ward.getId());
        if (foundWard.isPresent()) {
            Ward changedWard = foundWard.get();
            changedWard.setName(ward.getName());
            changedWard.setMaxCount(ward.getMaxCount());
            wardRepository.save(changedWard);
            return WardConverter.toWardDto(changedWard);
        }
        throw new IllegalArgumentException("Such ward with this id noo exists");
    }

    @Override
    public void deleteById(Long id) {
        wardRepository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        Optional<Ward> diagnosis = wardRepository.findByName(name);
        if (!diagnosis.isEmpty()) {
            wardRepository.delete(diagnosis.get());
        }
    }
}
