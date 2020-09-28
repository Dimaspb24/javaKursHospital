package com.bogdanov.project.hospital_admission.model;

import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"id"})
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NonNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NonNull
    @Column(name = "pather_name", nullable = false)
    private String patherName;

    /*"name = "Этот параметр объявляет столбец в целевой сущности, который будет использоваться для соединения. */
    @NonNull
    @ManyToOne
    @JoinColumn(name = "diagnosis_fk")
    private Diagnosis diagnosis;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "ward_fk")
    private Ward ward;
}
