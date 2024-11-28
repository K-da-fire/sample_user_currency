package com.sparta.currency_user.user.service;

import static com.sparta.currency_user.exception.ErrorCode.NOT_FOUND_USER;

import com.sparta.currency_user.currency.service.CurrencyService;
import com.sparta.currency_user.exception.NotFoundException;
import com.sparta.currency_user.exchange.repository.ExchangeRepository;
import com.sparta.currency_user.user.dto.UserRequestDto;
import com.sparta.currency_user.user.dto.UserResponseDto;
import com.sparta.currency_user.user.entity.User;
import com.sparta.currency_user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ExchangeRepository exchangeRepository;

    public UserResponseDto findById(Long id) {
        return new UserResponseDto(findUserById(id));
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_USER, NOT_FOUND_USER.getMessage()));
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream().map(UserResponseDto::toDto).toList();
    }

    @Transactional
    public UserResponseDto save(UserRequestDto userRequestDto) {
        User savedUser = userRepository.save(userRequestDto.toEntity());
        return new UserResponseDto(savedUser);
    }

    @Transactional
    public void deleteUserById(Long id) {
        exchangeRepository.deleteByUserId(id);
        this.findUserById(id);
        userRepository.deleteById(id);
    }
}
