package com.bogdanov.project.hospital_admission.services.api;

import com.bogdanov.project.hospital_admission.model.Ward;
import com.bogdanov.project.hospital_admission.utils.dto.WardDto;

import java.util.Set;

public interface WardService {

    WardDto findById(Long id);

    WardDto findByName(String name);

    Set<WardDto> findAll();

    WardDto saveWard(WardDto ward);

    void deleteById(Long id);
}
