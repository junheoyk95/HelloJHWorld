package com.juneng.hellojhworld.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juneng.hellojhworld.dto.LikesForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Likes {
    @Id
    @GeneratedValue
    @Column(name = "like_id")
    private Long id;
   // private String postId;
    private String userId;
    private LocalDateTime registDt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "post_id") // blogpostpostid -> postid로 명칭 변경
    private BlogPost blogPost;

    // 생성 메서드
    // 생성하는 시점을 변경할 일이 있으면 이부분만 고치면 되기때문에 이렇게 작성
    public static Likes createLike(LikesForm like) {
        BlogPost post = new BlogPost();
        post.setId(Long.parseLong(like.getPostId()));

        Likes likeE = new Likes();
        likeE.setUserId(like.getUserId());
        likeE.setRegistDt(LocalDateTime.now());
        likeE.setBlogPost(post);

        return likeE;
    }


}
