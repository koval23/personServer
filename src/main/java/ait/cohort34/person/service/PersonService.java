package ait.cohort34.person.service;

import ait.cohort34.person.dto.AddressDto;
import ait.cohort34.person.dto.PersonDto;
import ait.cohort34.person.dto.PopulationDto;

import java.util.List;

public interface PersonService {

    Boolean addPerson(PersonDto personDto);

    PersonDto findPersonById(Integer id);

    PersonDto deletePerson(Integer id);

    PersonDto updateName(Integer id, String name);

    List<PersonDto> findPersonsByCity(String city);

    List<PersonDto> findPersonsByAges(Integer from, Integer to);

    List<PersonDto> findPersonsByName(String name);

    List<PopulationDto> getCityPopulation();

    PersonDto updateAddress(Integer id, AddressDto addressDto);


}
