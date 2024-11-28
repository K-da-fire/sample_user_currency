package com.sparta.currency_user.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

  //커스텀
  @ExceptionHandler
  public ResponseEntity<ExceptionDto> notFoundException(NotFoundException e){
    return new ResponseEntity<>(new ExceptionDto(e.getErrorCode().getHttpStatus().toString(), e.getMessage()), e.getErrorCode().getHttpStatus());
  }

  //자바
  @ExceptionHandler
  public ResponseEntity<ExceptionDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    String message = e.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
    return new ResponseEntity<>(new ExceptionDto(HttpStatus.BAD_REQUEST.toString(), message), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionDto> handleDataIntegrityViolationException(
      DataIntegrityViolationException e) {
    String str = e.getMessage().split("'")[1];
    return new ResponseEntity<>(new ExceptionDto(HttpStatus.BAD_REQUEST.toString(), str + "은 중복입니다."), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> constraintViolationException(Exception e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

}
