package com.bogdanov.project.hospital_admission.restController;

import com.bogdanov.project.hospital_admission.model.Person;
import com.bogdanov.project.hospital_admission.services.api.PersonService;
import com.bogdanov.project.hospital_admission.utils.dto.PersonDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonsRestControllerV1 {

    private final PersonService personService;

    public PersonsRestControllerV1(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<Set<PersonDto>> findAllPersons() {
        Set<PersonDto> allPersons = personService.findAll();
        return new ResponseEntity<>(allPersons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<PersonDto> findPersonById(@PathVariable Long id) {
        PersonDto person = personService.findById(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<Set<PersonDto>> findPersonByName(@PathVariable String name) {
        Set<PersonDto> byLikeName = personService.findByLikeName(name);
        return new ResponseEntity<>(byLikeName, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<PersonDto> savePerson(@RequestBody PersonDto person) {
        return new ResponseEntity<>(personService.savePerson(person), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> deletePersonById(@PathVariable Long id) {
        personService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
