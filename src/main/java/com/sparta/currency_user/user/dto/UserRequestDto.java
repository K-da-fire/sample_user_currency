package com.sparta.currency_user.user.dto;

import com.sparta.currency_user.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @NotBlank(message = "이름은 필수 값입니다.")
    private String name;


    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일은 필수값입니다.")
    private String email;

    public User toEntity() {
        return new User(
                this.name,
                this.email
        );
    }
}
