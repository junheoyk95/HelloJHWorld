package com.juneng.hellojhworld.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class BlogPostForm {
    @NotEmpty(message = "POST 제목은 필수 입니다.")
    private String title;
    @NotEmpty(message = "POST 내용은 필수 입니다.")
    private String content;
    private String category;
    private String registUserId;
    private String registNickname;


}
