package com.sparta.currency_user.exchange.dto;

import com.sparta.currency_user.common.CurrencyStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ExchangeStatusDto {

  @NotBlank(message = "환전 요청 아이디는 필수 값입니다.")
  private Long exchangeId;

  @NotBlank(message = "유저 아이디는 필수 값입니다.")
  private Long userId;

  @NotBlank(message = "NORMAL / CANCEL 은(는) 필수 값입니다.")
  private CurrencyStatus currencyStatus;

}
