package com.juneng.hellojhworld.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginForm {

    // 빈값 들어오는 것을 방지
    @NotEmpty(message = "회원 ID은 필수 입니다.")
    private String userId;
    @NotEmpty(message = "회원 비밀번호는 필수 입니다.")
    private String password;

}
