package com.sparta.currency_user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

  //커스텀
  @ExceptionHandler
  public ResponseEntity<ExceptionDto> duplicatedException(DuplicatedException e){
    return new ResponseEntity<>(new ExceptionDto(e.getErrorCode().getHttpStatus().toString(), e.getMessage()), e.getErrorCode().getHttpStatus());
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionDto> internalServerException(InternalServerException e){
//    return new ResponseEntity<>(e.getErrorCode().getMessage(), e.getErrorCode().getHttpStatus());
    return new ResponseEntity<>(new ExceptionDto(e.getErrorCode().getHttpStatus().toString(), e.getMessage()), e.getErrorCode().getHttpStatus());
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionDto> invalidInputException(InvalidInputException e){
    return new ResponseEntity<>(new ExceptionDto(e.getErrorCode().getHttpStatus().toString(), e.getMessage()), e.getErrorCode().getHttpStatus());
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionDto> notFoundException(NotFoundException e){
    return new ResponseEntity<>(new ExceptionDto(e.getErrorCode().getHttpStatus().toString(), e.getMessage()), e.getErrorCode().getHttpStatus());
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionDto> noAuthorizedException(NoAuthorizedException e){
    return new ResponseEntity<>(new ExceptionDto(e.getErrorCode().getHttpStatus().toString(), e.getMessage()), e.getErrorCode().getHttpStatus());
  }

  //자바

  @ExceptionHandler
  public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> constraintViolationException(Exception e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

}
