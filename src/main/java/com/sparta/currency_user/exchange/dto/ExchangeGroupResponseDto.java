package com.sparta.currency_user.exchange.dto;

import com.sparta.currency_user.exchange.entity.Exchange;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExchangeGroupResponseDto {

  private Long count;

  private BigDecimal totalAmountInKrw;

}
