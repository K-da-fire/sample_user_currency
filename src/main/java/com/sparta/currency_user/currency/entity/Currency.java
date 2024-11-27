package com.sparta.currency_user.currency.entity;

import com.sparta.currency_user.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "currency")
@AllArgsConstructor
@NoArgsConstructor
public class Currency extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String currencyName;        //ex USD

    @Column(nullable = false)
    private BigDecimal exchangeRate;    //환율 (1430.00)

    private String symbol;              //ex $

    public Currency(String currencyName, BigDecimal exchangeRate, String symbol) {
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.symbol = symbol;
    }

    public void updateCurrency(BigDecimal exchangeRate){
        this.exchangeRate = exchangeRate;
    }
}