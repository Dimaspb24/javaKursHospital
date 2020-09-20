package com.bogdanov.project.hospital_admission.model;

import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "diagnoses")
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

//    @NonNull
//    @OneToMany(mappedBy = "diagnosis", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Person> persons;
//
//    public void addPerson(Person person) {
//        persons.add(person);
//        person.setDiagnosis(this);
//    }
//
//    public void removePerson(Person person) {
//        persons.remove(person);
//        person.setDiagnosis(null);
//    }
}