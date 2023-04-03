package com.juneng.hellojhworld.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

    // 빈값 들어오는 것을 방지
    @NotEmpty(message = "회원 ID은 필수 입니다.")
    private String userId;
    @NotEmpty(message = "회원 비밀번호는 필수 입니다.")
    private String password;
    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;
    @NotEmpty(message = "회원 닉네임은 필수 입니다.")
    private String nickname;
    private String memo;
}
