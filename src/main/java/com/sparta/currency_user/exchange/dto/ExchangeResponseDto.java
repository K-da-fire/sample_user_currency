package com.sparta.currency_user.exchange.dto;

import com.sparta.currency_user.common.CurrencyStatus;
import com.sparta.currency_user.currency.dto.CurrencyResponseDto;
import com.sparta.currency_user.currency.entity.Currency;
import com.sparta.currency_user.exchange.entity.Exchange;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExchangeResponseDto {

  private String username;

  private BigDecimal amountIn;

  private BigDecimal amountOut;

  private String symbol;

  private CurrencyStatus status;

  public static ExchangeResponseDto toDto(Exchange exchange) {
    return new ExchangeResponseDto(
        exchange.getUser().getName(),
        exchange.getAmountInKrw().setScale(0, BigDecimal.ROUND_HALF_UP),
        exchange.getAmountAfterExchange().setScale(exchange.getCurrency().getRound(), BigDecimal.ROUND_HALF_UP),
        exchange.getCurrency().getSymbol(),
        exchange.getStatus()
    );
  }

  public ExchangeResponseDto(Exchange exchange) {
    this.username = exchange.getUser().getName();
    this.amountIn = exchange.getAmountInKrw();
    //주어진 소수점에서 끊음
    this.amountOut = exchange.getAmountAfterExchange();
    this.symbol = exchange.getCurrency().getSymbol();
    this.status = exchange.getStatus();
  }
}
