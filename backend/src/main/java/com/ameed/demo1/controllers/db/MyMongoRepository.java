package com.ameed.demo1.controllers.db;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyMongoRepository extends MongoRepository<Person, String> {
}
