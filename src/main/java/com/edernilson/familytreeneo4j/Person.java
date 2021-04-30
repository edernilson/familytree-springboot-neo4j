package com.edernilson.familytreeneo4j;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.*;
import java.util.stream.Collectors;

@Node
public class Person {

    @Id @GeneratedValue
    private Long id;

    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @Relationship
    @JsonIgnoreProperties({"familyMember"})
    public Map<PersonType, List<Person>> familyMember;

    public void linkWith(PersonType type, Person person) {
        if (familyMember == null) {
            familyMember = new HashMap<>();
        }
        List<Person> listOfPerson = familyMember.get(type);
        if (listOfPerson == null) listOfPerson = new ArrayList<>();
        listOfPerson.add(person);
        familyMember.put(type, listOfPerson);
    }

    @Override
    public String toString() {
        return this.name + "s familymember => "
                + Optional.ofNullable(this.familyMember).orElse(
                Collections.emptyMap())
                .values()
                .stream()
                .flatMap(Collection::stream)
                .map(Person::getName)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
