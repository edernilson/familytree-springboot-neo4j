package com.edernilson.familytreeneo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends Neo4jRepository<Person, Long> {
    @Query("MATCH (a:Person) WHERE a.name = $name\n" +
            "OPTIONAL MATCH (a)-[r]->(b:Person)\n" +
            "RETURN a, collect(r), collect(b)")
    Person findByName(String name);

    @Query("MATCH (p1:Person)-[r]->(p2:Person) RETURN p1, collect(r), collect(p2)")
    List<Person> findAll(String name);

    @Query("MATCH (p1:Person)-[r]->(p2:Person) WHERE p2.name=$name RETURN p1, collect(r), collect(p2)")
    List<Person> findByFamilyMemberName(String name);
}
