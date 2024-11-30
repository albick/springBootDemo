package com.albick.demospringboot.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Collection;

@Document
@Data
@Builder
public class WorkoutDay {
    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private LocalDateTime date;

    @DBRef
    private Collection<Exercise> exercises;
}
