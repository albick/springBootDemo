package com.albick.demospringboot.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class ExerciseName {
    @Id
    private String id;

    @NotNull
    private String enumName;
}
