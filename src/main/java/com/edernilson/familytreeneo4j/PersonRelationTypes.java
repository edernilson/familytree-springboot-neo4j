package com.edernilson.familytreeneo4j;

public interface PersonRelationTypes {

    PersonType getType();

    Person getPersonName();

    void linkWith(Person person);
}
