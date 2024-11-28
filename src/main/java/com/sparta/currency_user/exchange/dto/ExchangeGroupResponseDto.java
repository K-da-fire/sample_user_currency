package com.sparta.currency_user.exchange.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExchangeGroupResponseDto {

  private String currencyName;

  private Long count;

  private BigDecimal totalAmountInKrw;
}
