package com.edernilson.familytreeneo4j;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import static com.edernilson.familytreeneo4j.PersonType.FILHO;

@RelationshipProperties
public class ConjugeRelationType implements PersonRelationTypes {

    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    Person person;

    public PersonType getType() {
        return FILHO;
    }

    @Override
    public Person getPersonName() {
        return this.person;
    }

    @Override
    public void linkWith(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
