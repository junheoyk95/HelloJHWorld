package com.juneng.hellojhworld.dto;

import com.juneng.hellojhworld.domain.BlogPost;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CommentForm {

    @Column(length = 2000)
    private String content;
    private String commentType;
    private String parentId;
    private String postId;
    private String registNickname;
    private String registUserId;
    private LocalDateTime registDt;




}
