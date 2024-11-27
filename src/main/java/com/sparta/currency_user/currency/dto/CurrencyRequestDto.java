package com.sparta.currency_user.currency.dto;

import com.sparta.currency_user.currency.entity.Currency;
import lombok.Getter;

import java.math.BigDecimal;
import org.springframework.format.annotation.NumberFormat;

@Getter
public class CurrencyRequestDto {

    //환전 신정한 사람의 이름
    private String currencyName;
    //현재 환전 율
    @NumberFormat(pattern = "#,###.##")
    private BigDecimal exchangeRate;
    //환전 단위(원(\) / 달러($))
    private String symbol;

    public Currency toEntity() {
        return new Currency(
                this.currencyName,
                this.exchangeRate,
                this.symbol
        );
    }
}
