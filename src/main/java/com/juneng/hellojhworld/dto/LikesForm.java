package com.juneng.hellojhworld.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class LikesForm {
    private Long likeId;
    private String postId;
    private String userId;
    private LocalDateTime registDt;

}
