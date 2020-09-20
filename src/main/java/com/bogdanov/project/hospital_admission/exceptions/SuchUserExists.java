package com.bogdanov.project.hospital_admission.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@Getter
public class SuchUserExists extends AuthenticationException {
    private HttpStatus httpStatus;

    public SuchUserExists(String msg) {
        super(msg);
    }

    public SuchUserExists(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}
