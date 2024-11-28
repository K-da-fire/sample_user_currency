package com.sparta.currency_user.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {


  // NotFoundException
  NOT_FOUND_CURRENCY("환전 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

  NOT_FOUND_USER("사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

  NOT_FOUND_EXCHANGE("환전 요청을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

  private final String message;
  private final HttpStatus httpStatus;

  ErrorCode(String message, HttpStatus httpStatus) {
    this.message = message;
    this.httpStatus = httpStatus;
  }
}
