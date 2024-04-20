package ait.cohort34.person.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Person {

    @Id
    Integer id;
    @Setter
    String name;
    private LocalDate birthDate;
    @Setter
    @Embedded
    private Address address;

}
