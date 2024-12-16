package com.albick.demospringboot.exception;

import lombok.Getter;

@Getter
public class RecordNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String tableName;

    public RecordNotFoundException(String tableName) {
        this.tableName = tableName;
    }
}
