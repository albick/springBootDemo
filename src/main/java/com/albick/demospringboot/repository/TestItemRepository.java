package com.albick.demospringboot.repository;

import com.albick.demospringboot.entity.TestItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestItemRepository extends MongoRepository<TestItem, String> {
}
