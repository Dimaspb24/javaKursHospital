package com.bogdanov.project.hospital_admission.restController;

import com.bogdanov.project.hospital_admission.model.Ward;
import com.bogdanov.project.hospital_admission.repository.UserRepository;
import com.bogdanov.project.hospital_admission.services.api.WardService;
import com.bogdanov.project.hospital_admission.utils.dto.WardDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/wards")
public class WardRestControllerV1 {

    private final WardService wardService;

    public WardRestControllerV1(WardService wardService) {
        this.wardService = wardService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<Set<WardDto>> findAllWards() {
        Set<WardDto> allWards = wardService.findAll();
        return new ResponseEntity<>(allWards, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<WardDto> findWardById(@PathVariable Long id) {
        WardDto ward = wardService.findById(id);
        return new ResponseEntity<>(ward, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<WardDto> findWardByName(@PathVariable String name) {
        WardDto ward = wardService.findByName(name);
        return new ResponseEntity<>(ward, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<WardDto> saveWard(@RequestBody WardDto ward) {
        return new ResponseEntity<>(wardService.saveWard(ward), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> deleteWardById(@PathVariable Long id) {
        wardService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
