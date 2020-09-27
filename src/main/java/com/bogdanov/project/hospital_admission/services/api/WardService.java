package com.bogdanov.project.hospital_admission.services.api;

import com.bogdanov.project.hospital_admission.utils.dto.WardDto;

import java.util.List;

public interface WardService {

    WardDto findById(Long id);

    WardDto findByName(String name);

    List<WardDto> findByNameContaining(String name);

    List<WardDto> findAll();

    WardDto saveOrUpdate(WardDto ward);

    void deleteById(Long id);

    void deleteByName(String name);
}
