package com.albick.demospringboot.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class WorkoutSet {
    @Id
    private String id;

    @NotNull
    @Min(0)
    private Integer reps;

    @NotNull
    @Min(0)
    private Double weight;
}
