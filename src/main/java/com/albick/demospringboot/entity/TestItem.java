package com.albick.demospringboot.entity;

import org.springframework.data.annotation.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class TestItem {
    @Id
    private String id;

    private String name;
}
