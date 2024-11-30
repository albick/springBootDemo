package com.albick.demospringboot.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Document
@Data
@Builder
public class Exercise {
    @Id
    private String id;

    private String description;

    @DBRef
    private ExerciseName exerciseName;

    @DBRef
    private Collection<WorkoutSet> sets;
}
