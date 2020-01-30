package com.app.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.exception.MyException;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MyException.class)
    public void test() {

    }
}
