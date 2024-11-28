package com.sparta.currency_user.exchange.controller;

import com.sparta.currency_user.exchange.dto.ExchangeGroupResponseDto;
import com.sparta.currency_user.exchange.dto.ExchangeRequestDto;
import com.sparta.currency_user.exchange.dto.ExchangeResponseDto;
import com.sparta.currency_user.exchange.dto.ExchangeStatusDto;
import com.sparta.currency_user.exchange.service.ExchangeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchanges")
@RequiredArgsConstructor
public class ExchangeController {

  private final ExchangeService exchangeService;

  @PostMapping
  public ResponseEntity<ExchangeResponseDto> save(
      @Valid @RequestBody ExchangeRequestDto exchangeRequestDto
  ){
    ExchangeResponseDto exchangeResponseDto = exchangeService.save(exchangeRequestDto.getUserId(), exchangeRequestDto.getCurrencyId(), exchangeRequestDto.getAmountIn());
    return ResponseEntity.ok().body(exchangeResponseDto);
  }

  @GetMapping
  public ResponseEntity<List<ExchangeResponseDto>> getExchanges(){
    return ResponseEntity.ok().body(exchangeService.getExchanges());
  }

  @GetMapping("/users/{userId}")
  public ResponseEntity<List<ExchangeResponseDto>> getExchangesByUserId(
      @PathVariable("userId") Long userId
  ){
    return ResponseEntity.ok().body(exchangeService.getExchangesByUserId(userId));
  }

  @GetMapping("/users/{userId}/group")
  public ResponseEntity<List<ExchangeGroupResponseDto>> getExchangesGroupByUserId(
      @PathVariable("userId") Long userId
  ){
    return ResponseEntity.ok().body(exchangeService.getExchangesGroupByUserId(userId));
  }

  @PatchMapping
  public ResponseEntity<ExchangeResponseDto> update(
      @Valid @RequestBody ExchangeStatusDto exchangeStatusDto
  ){
    ExchangeResponseDto exchangeResponseDto = exchangeService.update(exchangeStatusDto.getExchangeId(), exchangeStatusDto.getUserId());
    return ResponseEntity.ok().body(exchangeResponseDto);
  }
}
