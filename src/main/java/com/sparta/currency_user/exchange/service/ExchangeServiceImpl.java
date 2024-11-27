package com.sparta.currency_user.exchange.service;

import com.sparta.currency_user.common.CurrencyStatus;
import com.sparta.currency_user.currency.entity.Currency;
import com.sparta.currency_user.currency.service.CurrencyService;
import com.sparta.currency_user.exchange.dto.ExchangeResponseDto;
import com.sparta.currency_user.exchange.entity.Exchange;
import com.sparta.currency_user.exchange.repository.ExchangeRepository;
import com.sparta.currency_user.user.entity.User;
import com.sparta.currency_user.user.service.UserService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService{

  private final ExchangeRepository exchangeRepository;
  private final UserService userService;
  private final CurrencyService currencyService;

  @Override
  public ExchangeResponseDto save(Long userId, Long currencyId, BigDecimal amountIn) {
    User user = userService.findUserById(userId);
    Currency currency = currencyService.findCurrencyById(currencyId);
    BigDecimal amountOut = amountIn.divide(currency.getExchangeRate(), 2, RoundingMode.HALF_UP);
    Exchange exchange = new Exchange(user, currency, amountIn, amountOut, CurrencyStatus.NORMAL);
    exchangeRepository.save(exchange);
    return new ExchangeResponseDto(exchange);
  }

  @Override
  public List<ExchangeResponseDto> getExchanges() {
    return exchangeRepository.findAll().stream().map(ExchangeResponseDto::toDto).toList();
  }

  @Transactional
  @Override
  public ExchangeResponseDto update(Long exchangeId, Long userId, CurrencyStatus status) {
//    User user = userService.findUserById(userId);
    Exchange exchange = findByIdOrElseThrow(exchangeId);
    exchange.updateStatus(status);
    return new ExchangeResponseDto(exchange);
  }

  private Exchange findByIdOrElseThrow(Long id) {
    return exchangeRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Not Found Exchange"));
  }
}
