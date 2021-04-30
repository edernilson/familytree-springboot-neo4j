package com.edernilson.familytreeneo4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableNeo4jRepositories
public class FamilytreeNeo4jApplication {

    private final static Logger log = LoggerFactory.getLogger(FamilytreeNeo4jApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FamilytreeNeo4jApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(PersonRepository personRepository) {
        return args -> {

            personRepository.deleteAll();

            Person eder = new Person("Eder Nilson");
            Person gleiciane = new Person("Maria Gleiciane");
            Person lara = new Person("Lara Maria");

            List<Person> family = Arrays.asList(eder, gleiciane, lara);

            log.info("Antes de linkar com Neo4j...");

            family.forEach(person -> log.info("\t" + person.toString()));

            personRepository.save(eder);
            personRepository.save(gleiciane);
            personRepository.save(lara);

//            PersonRelationTypes filho = new FilhoRelationType();
//            filho.linkWith(lara);
//            PersonRelationTypes conjuge = new ConjugeRelationType();
//            filho.linkWith(gleiciane);
//            PersonRelationTypes pai = new PaiRelationType();
//            filho.linkWith(eder);

//            lara = personRepository.findByName(lara.getName());
//            lara.linkWith(PersonType.FILHO, eder);
//            lara.linkWith(PersonType.FILHO, gleiciane);
//            personRepository.save(lara);

            eder = personRepository.findByName(eder.getName());
            eder.linkWith(PersonType.PAI, lara);
            personRepository.save(eder);

            gleiciane = personRepository.findByName(gleiciane.getName());
            gleiciane.linkWith(PersonType.MAE, lara);
            gleiciane.linkWith(PersonType.CONJUGE, eder);
            personRepository.save(gleiciane);

            log.info("Print cada pessoa por nome...");
            personRepository.findAll().forEach(person -> log.info("\t" + person));

            family = personRepository.findByFamilyMemberName(eder.getName());
            log.info("Pessoas que tem o eder na familia...");
            family.forEach(person -> log.info("\t" + person));
        };
    }
}
