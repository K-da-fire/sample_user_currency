package com.sparta.currency_user.exchange.service;

import com.sparta.currency_user.exchange.dto.ExchangeGroupResponseDto;
import com.sparta.currency_user.exchange.dto.ExchangeResponseDto;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface ExchangeService {

  ExchangeResponseDto save(Long userId, Long currencyId, BigDecimal amountIn);

  ExchangeResponseDto update(Long userId, Long currencyName);

  List<ExchangeResponseDto> getExchanges();

  List<ExchangeGroupResponseDto> getExchangesGroupByUserId(Long userId);

  List<ExchangeResponseDto> getExchangesByUserId(Long userId);
}
