package com.bogdanov.project.hospital_admission.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "diagnosis_fk", nullable = false)
    private Diagnosis diagnosis;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ward_fk", nullable = false)
    private Ward ward;
}
