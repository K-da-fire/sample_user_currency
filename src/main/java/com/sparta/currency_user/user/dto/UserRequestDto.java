package com.sparta.currency_user.user.dto;

import com.sparta.currency_user.user.entity.User;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private String name;


    @Pattern(regexp = "^[a-zA-Z0-9]+@[0-9a-zA-Z]+\\.[a-z]$", message = "이메일 형식이 아닙니다.")
    private String email;

    public User toEntity() {
        return new User(
                this.name,
                this.email
        );
    }
}
