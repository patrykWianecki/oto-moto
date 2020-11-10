package com.app.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.model.ClientException;
import com.app.model.CustomErrorResponse;

import static com.app.constants.ClientConstants.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionController {

  @ExceptionHandler(value = NotFoundException.class)
  public ResponseEntity<CustomErrorResponse> handleGenericNotFoundException(NotFoundException e) {
    return new ResponseEntity<>(createCustomErrorResponse(e, ELEMENT_NOT_FOUND_MESSAGE), NOT_FOUND);
  }

  @ExceptionHandler(value = EmptyResultDataAccessException.class)
  public ResponseEntity<CustomErrorResponse> handleGenericNotFoundException(
      EmptyResultDataAccessException e) {
    return new ResponseEntity<>(createCustomErrorResponse(e, CLASS_NOT_FOUND_MESSAGE), NOT_FOUND);
  }

  @ExceptionHandler(value = IOException.class)
  public ResponseEntity<CustomErrorResponse> handleGenericNotFoundException(IOException e) {
    return new ResponseEntity<>(createCustomErrorResponse(e, INCORRECT_FILE_MESSAGE), NOT_FOUND);
  }

  @ExceptionHandler(value = ClientException.class)
  public ResponseEntity<CustomErrorResponse> handleValidationException(ClientException e) {
    return new ResponseEntity<>(
        createCustomErrorResponse(e, FILE_VALIDATION_ERROR_MESSAGE), NOT_FOUND
    );
  }

  private static CustomErrorResponse createCustomErrorResponse(Exception e, String errorCode) {
    return CustomErrorResponse.builder()
        .errorCode(errorCode)
        .errorMessage(e.getMessage())
        .timestamp(LocalDateTime.now())
        .status(NOT_FOUND.value())
        .build();
  }
}
