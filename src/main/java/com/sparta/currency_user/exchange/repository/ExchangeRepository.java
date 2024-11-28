package com.sparta.currency_user.exchange.repository;

import com.sparta.currency_user.exchange.dto.ExchangeGroupResponseDto;
import com.sparta.currency_user.exchange.entity.Exchange;
import jakarta.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

  void deleteByUserId(Long id);

  Collection<Exchange> findByUserId(Long userId);

//  Collection<Object> findGroupByUserId(Long userId);

//  @Query("select count(e) as count, sum(e.amountAfterExchange) as totalAmountInKrw "
//      + "from Exchange e "
//      + "where e.user.id = :userId "
//      + "group by e.user.id")
//  Query<Integer, BigDecimal> findGroupByUserId(Long userId);
}
