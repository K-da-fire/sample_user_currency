package com.sparta.currency_user.currency.controller;

import com.sparta.currency_user.currency.dto.CurrencyRequestDto;
import com.sparta.currency_user.currency.dto.CurrencyResponseDto;
import com.sparta.currency_user.currency.service.CurrencyService;
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

    // 환전 신청
    @PostMapping
    public ResponseEntity<CurrencyResponseDto> createCurrency(@RequestBody CurrencyRequestDto currencyRequestDto) {
        return ResponseEntity.ok().body(currencyService.save(currencyRequestDto));
    }

    // 환율 갱신
    @PatchMapping
    public ResponseEntity<CurrencyResponseDto> updateCurrency(@RequestBody CurrencyRequestDto currencyRequestDto) {
        return ResponseEntity.ok().body(currencyService.update(currencyRequestDto));
    }
}
