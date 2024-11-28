package com.sparta.currency_user.currency.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.sparta.currency_user.currency.dto.CurrencyRequestDto;
import com.sparta.currency_user.currency.dto.CurrencyResponseDto;
import com.sparta.currency_user.currency.service.CurrencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    // 환율 전체 조회
    @GetMapping
    public ResponseEntity<List<CurrencyResponseDto>> findCurrencies() {
        return ResponseEntity.ok().body(currencyService.findAll());
    }

    // 환율 아이디로 단일 조회
    @GetMapping("/{id}")
    public ResponseEntity<CurrencyResponseDto> findCurrency(@PathVariable Long id) {
        return ResponseEntity.ok().body(currencyService.findById(id));
    }

    // 환율 정보 등록
    @PostMapping
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ResponseEntity<CurrencyResponseDto> createCurrency(@Valid @RequestBody CurrencyRequestDto currencyRequestDto) {
        return ResponseEntity.ok().body(currencyService.save(currencyRequestDto));
    }

    // 환율 정보 갱신
    @PatchMapping
    public ResponseEntity<CurrencyResponseDto> updateCurrency(@Valid @RequestBody CurrencyRequestDto currencyRequestDto) {
        return ResponseEntity.ok().body(currencyService.update(currencyRequestDto));
    }
}
