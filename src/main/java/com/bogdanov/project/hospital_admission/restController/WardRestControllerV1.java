package com.bogdanov.project.hospital_admission.restController;

import com.bogdanov.project.hospital_admission.services.api.WardService;
import com.bogdanov.project.hospital_admission.utils.dto.WardDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wards")
public class WardRestControllerV1 {

    private final WardService wardService;

    public WardRestControllerV1(WardService wardService) {
        this.wardService = wardService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<WardDto>> findAllWards() {
        List<WardDto> allWards = wardService.findAll();
        return new ResponseEntity<>(allWards, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<WardDto> findWardById(@PathVariable Long id) {
        WardDto ward = wardService.findById(id);
        return new ResponseEntity<>(ward, HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<WardDto>> findWardByName(@PathVariable String name) {
        List<WardDto> ward = wardService.findByNameContaining(name);
        return new ResponseEntity<>(ward, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<WardDto> saveWard(@RequestBody WardDto ward) {
        return new ResponseEntity<>(wardService.saveOrUpdate(ward), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> deleteWardById(@PathVariable Long id) {
        wardService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
