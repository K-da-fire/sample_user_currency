package com.sparta.currency_user.exchange.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ExchangeStatusDto {

  @NotNull(message = "환전 요청 아이디는 필수 값입니다.")
  private Long exchangeId;

  @NotNull(message = "유저 아이디는 필수 값입니다.")
  private Long userId;
}
