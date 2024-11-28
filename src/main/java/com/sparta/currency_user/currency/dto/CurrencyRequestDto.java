package com.sparta.currency_user.currency.dto;

import com.sparta.currency_user.currency.entity.Currency;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;
import org.springframework.format.annotation.NumberFormat;

@Getter
public class CurrencyRequestDto {

    @NotBlank(message = "통화표시는 빈값일 수 없습니다.")
    @Column(length = 3)
    private String currencyName;
    //현재 환전 율
    @NumberFormat(pattern = "#,###.##")
    @NotNull(message = "환율 정보는 빈값일 수 없습니다.")
    private BigDecimal exchangeRate;
    //환전 단위(원(\) / 달러($))
    @NotBlank(message = "통화단위는 빈값일 수 없습니다.")
    private String symbol;

    @NotNull(message = "소수 자리수는 빈값일 수 없습니다.")
    private Integer round;

    public Currency toEntity() {
        return new Currency(
            this.currencyName,
            this.exchangeRate,
            this.symbol,
            this.round
        );
    }
}
