package com.juneng.hellojhworld.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juneng.hellojhworld.dto.CommentForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;
    @Column(length = 2000)
    private String content;
    private String commentType;
    private String parentId;
    private String registNickname;
    private String registUserId;
    private LocalDateTime registDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "post_id") // blogpostpostid -> postid로 명칭 변경
    private BlogPost blogPost;

    // 생성 메서드
    // 생성하는 시점을 변경할 일이 있으면 이부분만 고치면 되기때문에 이렇게 작성
    public static Comment createComment(CommentForm commentForm) {
        BlogPost post = new BlogPost();
        post.setId(Long.parseLong(commentForm.getPostId()));

        Comment comment = new Comment();
        comment.setContent(commentForm.getContent());
        comment.setCommentType(commentForm.getCommentType());
        comment.setParentId(commentForm.getParentId());
        comment.setRegistNickname(commentForm.getRegistNickname());
        comment.setRegistUserId(commentForm.getRegistUserId());
        comment.setRegistDt(LocalDateTime.now());
        comment.setBlogPost(post);

        return comment;
    }


}
