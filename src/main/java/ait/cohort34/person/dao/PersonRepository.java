package ait.cohort34.person.dao;

import ait.cohort34.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Stream<Person> findByAddress_City(String city);

    List<Person> findByBirthDateBetween(LocalDate from, LocalDate to);

    Stream<Person> findByName(String name);

}
