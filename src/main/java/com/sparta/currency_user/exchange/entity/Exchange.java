package com.sparta.currency_user.exchange.entity;

import com.sparta.currency_user.common.BaseEntity;
import com.sparta.currency_user.common.CurrencyStatus;
import com.sparta.currency_user.currency.entity.Currency;
import com.sparta.currency_user.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Exchange extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "to_currency_id")
  private Currency currency;

  private BigDecimal amountInKrw; // 원 정수

  private BigDecimal amountAfterExchange; // 달러 실수

  @Enumerated(value = EnumType.STRING)
  private CurrencyStatus status;

  public Exchange(User user, Currency currency, BigDecimal amountInKrw, BigDecimal amountAfterExchange, CurrencyStatus status) {
    this.user = user;
    this.currency = currency;
    this.amountInKrw = amountInKrw;
    this.amountAfterExchange = amountAfterExchange;
    this.status = status;
  }

  public void updateStatus(CurrencyStatus status) {
    this.status = status;
  }

}
