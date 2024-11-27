package com.sparta.currency_user.currency.service;

import com.sparta.currency_user.currency.dto.CurrencyRequestDto;
import com.sparta.currency_user.currency.entity.Currency;
import com.sparta.currency_user.currency.dto.CurrencyResponseDto;
import com.sparta.currency_user.currency.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    // 환전 아이디로 단일 조회
    public CurrencyResponseDto findById(Long id) {
        return new CurrencyResponseDto(findCurrencyById(id));
    }

    // 환전 단일 조회를 위한 메소드(Service에서 상용)
    public Currency findCurrencyById(Long id) {
        return currencyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("통화를 찾을 수 없습니다."));
    }

    // 환전 전체 조회
    public List<CurrencyResponseDto> findAll() {
        return currencyRepository.findAll().stream().map(CurrencyResponseDto::toDto).toList();
    }

    // 환전 신청
    @Transactional
    public CurrencyResponseDto save(CurrencyRequestDto currencyRequestDto) {
        Currency savedCurrency = currencyRepository.save(currencyRequestDto.toEntity());
        return new CurrencyResponseDto(savedCurrency);
    }

    // 환율 갱신
    @Transactional
    public CurrencyResponseDto update(CurrencyRequestDto currencyRequestDto) {
        Currency updatedCurrency = currencyRepository.findByCurrencyName(currencyRequestDto.getCurrencyName());
        updatedCurrency.updateCurrency(currencyRequestDto.getExchangeRate());
        return new CurrencyResponseDto(updatedCurrency);
    }
}
