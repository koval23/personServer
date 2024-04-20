package ait.cohort34.person.controller;

import ait.cohort34.person.dto.AddressDto;
import ait.cohort34.person.dto.PersonDto;
import ait.cohort34.person.dto.PopulationDto;
import ait.cohort34.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public boolean addPerson(@RequestBody PersonDto personDto) {
        return personService.addPerson(personDto);
    }

    @GetMapping("/{id}")
    public PersonDto findPersonById(@PathVariable Integer id) {
        return personService.findPersonById(id);
    }

    @DeleteMapping("/{id}")
    public PersonDto deletePerson(@PathVariable Integer id) {
        return personService.deletePerson(id);
    }

    @PutMapping("/{id}/name/{name}")
    public PersonDto updateName(@PathVariable Integer id,
                                @PathVariable String name) {
        return personService.updateName(id, name);
    }

    @GetMapping("/city/{city}")
    public List<PersonDto> findPersonsByCity(@PathVariable String city) {
        return personService.findPersonsByCity(city);
    }

    @GetMapping("/ages/{to}/{from}")
    public List<PersonDto> findPersonsByAges(@PathVariable Integer from,
                                             @PathVariable Integer to) {
        return personService.findPersonsByAges(from, to);
    }

    @GetMapping("/name/{name}")
    public List<PersonDto> findPersonsByName(@PathVariable String name) {
        return personService.findPersonsByName(name);
    }

    @GetMapping("/population/")
    public List<PopulationDto> getCityPopulation() {
        return personService.getCityPopulation();
    }

    @PutMapping("{id}/address")
    public PersonDto updateAddress(@PathVariable Integer id,
                                   @RequestBody AddressDto addressDto) {
        return personService.updateAddress(id, addressDto);
    }


}
