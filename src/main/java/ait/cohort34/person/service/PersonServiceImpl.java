package ait.cohort34.person.service;

import ait.cohort34.person.dao.PersonRepository;
import ait.cohort34.person.dto.AddressDto;
import ait.cohort34.person.dto.PersonDto;
import ait.cohort34.person.dto.PopulationDto;
import ait.cohort34.person.dto.exeption.NotFoundPerson;
import ait.cohort34.person.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    @Override
    public Boolean addPerson(PersonDto personDto) {
        if (personRepository.existsById(personDto.getId())) {
            return false;
        }
        personRepository.save(modelMapper.map(personDto, Person.class));
        return true;
    }

    @Override
    public PersonDto findPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(NotFoundPerson::new);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public PersonDto deletePerson(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(NotFoundPerson::new);
        personRepository.deleteById(id);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public PersonDto updateName(Integer id, String name) {
        Person person = personRepository.findById(id).orElseThrow(NotFoundPerson::new);
        person.setName(name);
        personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public PersonDto updateAddress(Integer id, AddressDto addressDto) {
        Person person = personRepository.findById(id).orElseThrow(NotFoundPerson::new);
        if (addressDto != null) {
            if (addressDto.getCity() != null) {
                person.getAddress().setCity(addressDto.getCity());
            }
            if (addressDto.getBuilding() != null) {
                person.getAddress().setBuilding(addressDto.getBuilding());
            }
            if (addressDto.getStreet() != null) {
                person.getAddress().setStreet(addressDto.getStreet());
            }
            personRepository.save(person);
        }
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public List<PersonDto> findPersonsByCity(String city) {
        if (city != null) {
            return personRepository.findByAddress_City(city)
                    .map(person -> modelMapper.map(person, PersonDto.class))
                    .toList();
        } else {
            return Collections.emptyList();
        }

    }

    @Override
    public List<PersonDto> findPersonsByAges(Integer from, Integer to) {
        if (from >= 0 && to < from) {
            LocalDate fromDate = LocalDate.now().minusYears(from);
            LocalDate toDate = LocalDate.now().minusYears(to);
            System.out.println(fromDate);
            System.out.println(toDate);
            return personRepository.findByBirthDateBetween(fromDate,toDate).stream()
                    .map(person -> modelMapper.map(person, PersonDto.class))
                    .toList();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<PersonDto> findPersonsByName(String name) {
        if (name != null) {
            return personRepository.findByName(name)
                    .map(person -> modelMapper.map(person, PersonDto.class))
                    .toList();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<PopulationDto> getCityPopulation() {
        Map<String, Integer> result = new HashMap<>();
        personRepository.findAll().forEach(person -> {
            String city = person.getAddress().getCity();
            result.merge(city, 1, Integer::sum);
        });
        return result.entrySet().stream()
                .map(entry -> new PopulationDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

}
