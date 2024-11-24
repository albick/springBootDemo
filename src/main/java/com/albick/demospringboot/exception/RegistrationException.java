package com.albick.demospringboot.exception;

import lombok.Getter;

@Getter
public class RegistrationException extends Exception {
    private static final long serialVersionUID = 1L;

    private String msg;

    public RegistrationException(String msg) {
        this.msg = msg;
    }
}
