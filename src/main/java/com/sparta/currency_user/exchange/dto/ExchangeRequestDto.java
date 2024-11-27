package com.sparta.currency_user.exchange.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class ExchangeRequestDto {

  private Long userId;

  private Long currencyId;

  @NotBlank(message = "환전 금액은 필수 값입니다.")
  @DecimalMin(value = "1000", message = "환전 최소금액은 1000원 입니다.")
  @DecimalMax(value = "100000000", message = "환전 최대 금액은 1억원 입니다.")
  private BigDecimal amountIn;
}
