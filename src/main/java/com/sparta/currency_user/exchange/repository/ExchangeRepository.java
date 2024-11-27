package com.sparta.currency_user.exchange.repository;

import com.sparta.currency_user.exchange.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

  void deleteByUserId(Long id);
}
