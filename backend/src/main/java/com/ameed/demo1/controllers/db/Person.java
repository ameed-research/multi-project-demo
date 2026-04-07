package com.ameed.demo1.controllers.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MongoDB document mapping for the "persons" collection.
 */
@Document(collection = "persons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    private String id;

    private String firstName;

    private String lastName;
}
