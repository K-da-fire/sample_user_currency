package com.sparta.currency_user.exchange.service;


import static com.sparta.currency_user.exception.ErrorCode.NOT_FOUND_EXCHANGE;
import com.sparta.currency_user.common.CurrencyStatus;
import com.sparta.currency_user.currency.entity.Currency;
import com.sparta.currency_user.currency.service.CurrencyService;
import com.sparta.currency_user.exception.NotFoundException;
import com.sparta.currency_user.exchange.dto.ExchangeGroupResponseDto;
import com.sparta.currency_user.exchange.dto.ExchangeResponseDto;
import com.sparta.currency_user.exchange.entity.Exchange;
import com.sparta.currency_user.exchange.repository.ExchangeRepository;
import com.sparta.currency_user.user.entity.User;
import com.sparta.currency_user.user.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService{

  private final ExchangeRepository exchangeRepository;
  private final UserService userService;
  private final CurrencyService currencyService;
  private final EntityManager entityManager;

  @Transactional
  @PostConstruct
  public void init() {
    String query = "select c "
        + "from Currency c";
    List<Currency> currencies = entityManager.createQuery(query, Currency.class).getResultList();
    currencies.forEach(currency ->{
//      if(currency.getExchangeRate().scale() <= BigDecimal.ZERO.scale()) {
      if(BigDecimal.ZERO.compareTo(currency.getExchangeRate()) >= 0) {
        log.info("국가 코드 : {}", currency.getCurrencyName());
        log.info("유효하지 않은 환율 값입니다. exchangeRate = {} ", currency.getExchangeRate());
        exchangeRepository.deleteById(currency.getId());
        log.info("국가 코드 : {}의 환율정보를 삭제하였습니다.", currency.getCurrencyName());
      }
    });
  }

  @Override
  public ExchangeResponseDto save(Long userId, Long currencyId, BigDecimal amountIn) {
    User user = userService.findUserById(userId);
    Currency currency = currencyService.findCurrencyById(currencyId);

    BigDecimal divideRate = currency.getExchangeRate();
    // 100단위로 고시되는 4개의 국가 예외처리
    // 출처 : https://spib.wooribank.com/pib/Dream?withyou=CMCOM0184&utm_source=chatgpt.com
    if("JPY".equals(currency.getCurrencyName()) ||  // 일본
        "IDR".equals(currency.getCurrencyName()) || // 인도네시아
        "VND".equals(currency.getCurrencyName()) || // 베트남
        "KHR".equals(currency.getCurrencyName())    // 캄보디아
    ) {
      divideRate = divideRate.divide(new BigDecimal("100"));
    }
    BigDecimal amountOut = amountIn.divide(divideRate, currency.getRound(), RoundingMode.HALF_UP);
    Exchange exchange = new Exchange(user, currency, amountIn, amountOut, CurrencyStatus.NORMAL);
    exchangeRepository.save(exchange);
    return new ExchangeResponseDto(exchange);
  }

  @Override
  public List<ExchangeResponseDto> getExchanges() {
    return exchangeRepository.findAll().stream().map(ExchangeResponseDto::toDto).toList();
  }

  @Override
  public List<ExchangeGroupResponseDto> getExchangesGroupByUserId(Long userId) {
    String query = "select e.currency.currencyName as currencyName, count(e) as count, sum(e.amountAfterExchange) as totalAmountInKrw "
        + "from Exchange e "
        + "where e.user.id = :userId and e.status = :status "
        + "group by e.currency.id";
    return entityManager.createQuery(query, ExchangeGroupResponseDto.class)
        .setParameter("userId", userId)
        .setParameter("status", CurrencyStatus.NORMAL)
        .getResultList();
  }

  @Override
  public List<ExchangeResponseDto> getExchangesByUserId(Long userId) {
    return exchangeRepository.findByUserId(userId).stream().map(ExchangeResponseDto::toDto).toList();
  }

  @Transactional
  @Override
  public ExchangeResponseDto update(Long exchangeId, Long userId) {
//    User user = userService.findUserById(userId);
    Exchange exchange = findByIdOrElseThrow(exchangeId);
    CurrencyStatus status;
    if(exchange.getStatus().equals(CurrencyStatus.NORMAL)) {
      status = CurrencyStatus.CANCEL;
    }else{
      status = CurrencyStatus.NORMAL;
    }
    exchange.updateStatus(status);
    return new ExchangeResponseDto(exchange);
  }

  private Exchange findByIdOrElseThrow(Long id) {
    return exchangeRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(NOT_FOUND_EXCHANGE, NOT_FOUND_EXCHANGE.getMessage()));
  }
}
