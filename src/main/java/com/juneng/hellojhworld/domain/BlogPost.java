package com.juneng.hellojhworld.domain;

import com.juneng.hellojhworld.dto.BlogPostForm;
import com.juneng.hellojhworld.dto.MemberForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class BlogPost {
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;
    private String title;
    @Column(length = 2000)
    private String content;
    private String category;
    private int likeCnt;
    private String registUserId;
    private String registNickname;
    private LocalDateTime registDt;

    // 게시물이 삭제되면 댓글 데이터도 삭제하기 위함으로 설정 , 다른 영향은 없는지 확인 필요
    @OneToMany(mappedBy = "blogPost", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    // 게시물이 삭제되면 likes 데이터도 삭제하기 위함으로 설정 , 다른 영향은 없는지 확인 필요
    @OneToMany(mappedBy = "blogPost", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Likes> likes = new ArrayList<>();

    // 생성 메서드
    // 생성하는 시점을 변경할 일이 있으면 이부분만 고치면 되기때문에 이렇게 작성
    public static BlogPost createBlogPost(BlogPostForm blogPostForm) {
        BlogPost blogPost = new BlogPost();

        blogPost.setRegistDt(LocalDateTime.now());

        return blogPost;
    }

    public void addLike() {
        this.likeCnt += 1;
    }

    public void removeLike() {
        int resultCnt = this.likeCnt - 1;
        if(resultCnt < 0) {
            // 에러 처리 추가 필요
            // throw new Exception("");
        }
        this.likeCnt = resultCnt;
    }

}
