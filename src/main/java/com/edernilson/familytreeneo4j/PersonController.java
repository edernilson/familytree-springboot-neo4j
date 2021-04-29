package com.edernilson.familytreeneo4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    @GetMapping("/{name}")
    public Iterable<Person> getFamilyMemberName(@PathVariable String name) {
        return personRepository.findByFamilyMemberName(name);
    }
}
