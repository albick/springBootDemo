package com.albick.demospringboot.exception;

import lombok.Getter;

public class RecordNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    @Getter
    private String tableName;

    public RecordNotFoundException(String tableName) {
        this.tableName = tableName;
    }
}
