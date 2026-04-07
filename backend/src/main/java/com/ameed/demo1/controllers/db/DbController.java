package com.ameed.demo1.controllers.db;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/test/db")
@RequiredArgsConstructor
@Slf4j
public class DbController {

    private final MyMongoRepository repository;

    @GetMapping("/create")
    public Map<String, Object> createPerson() {
        var person = new Person(null, "John", "Doe");
        var savedPerson = repository.save(person);
        log.info("Saved person {}", savedPerson);
        return Map.of("id", savedPerson.getId(), "firstName", savedPerson.getFirstName(), "lastName", savedPerson.getLastName());
    }

    @GetMapping("/list")
    public Map<String, Object> getAll() {
        var all = repository.findAll();
        log.info("Found {} persons", all.size());
        return Map.of("size", all.size(), "persons", all);
    }
}
