package com.sparta.currency_user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public class ExceptionDto {

  private String errorCode;

  private String message;

}
