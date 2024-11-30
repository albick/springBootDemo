package com.albick.demospringboot.controller;

import com.albick.demospringboot.entity.TestItem;
import com.albick.demospringboot.repository.TestItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/testitem")
@RequiredArgsConstructor
public class TestItemController {

    private final TestItemRepository testItemRepository;

    @PostMapping
    public ResponseEntity<TestItem> postTestItem(@RequestBody TestItem testItem) {
        return new ResponseEntity<>(testItemRepository.save(testItem), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TestItem>> getAllTestItems() {
        return new ResponseEntity<>(testItemRepository.findAll(), HttpStatus.OK);
    }
}
