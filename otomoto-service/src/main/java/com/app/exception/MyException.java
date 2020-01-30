package com.app.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RestControllerAdvice
@RequiredArgsConstructor
public class MyException extends RuntimeException {

    private String message;
}
