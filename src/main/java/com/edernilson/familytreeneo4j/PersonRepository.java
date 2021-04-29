package com.edernilson.familytreeneo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends Neo4jRepository<Person, Long> {
    Person findByName(String name);

    @Query("MATCH (p1:Person)-[r]->(p2:Person) WHERE p2.name=$name RETURN p1 ")
    List<Person> findByFamilyMemberName(String name);
}
