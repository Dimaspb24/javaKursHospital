package com.bogdanov.project.hospital_admission.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@ToString(exclude = {"id", "persons"})
@Table(name = "wards")
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @NonNull
    @Column(name = "max_count", nullable = false)
    private Integer maxCount;

    /*при разрыве отношения Личности с конкретной палатой (мб стоять null или другая палата):
    * Если указано orphanRemoval = true, отключенный экземпляр Person автоматически удаляется.
    * Это полезно для очистки зависимых объектов (например, людей),
    * которые не должны существовать без ссылки от объекта-владельца
     * */

    //Удаление осиротевших объектов из коллекции
    //Включает CascadeType.REMOVE

    /*Аргумент orphanRemoval=true сообщает фреймворку Hibernate, что тот должен
    навсегда удалять объекты Person при удалении их из коллекции. */
    /*здесь должен быть запрос по персон с определённой палатой*/
//    @NonNull
//    @OneToMany(mappedBy = "ward", cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
//    private Set<Person> persons;
//
//
//    public void addPerson(Person person) {
//        persons.add(person);
//        person.setWard(this);
//    }
//
//    public void removePerson(Person person) {
//        persons.remove(person);
//        person.setWard(null);
//    }
}
