package com.prueba.blockchain.pruebablockchain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerController {

  @ExceptionHandler(Exception.class)
  public ResponseEntity handleException(Exception ex) {
    return ResponseEntity
        .badRequest()
        .body(ex.getMessage());
  }
}
