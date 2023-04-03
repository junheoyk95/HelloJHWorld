package com.juneng.hellojhworld.service;

import com.juneng.hellojhworld.domain.BlogPost;
import com.juneng.hellojhworld.domain.Likes;
import com.juneng.hellojhworld.dto.LikesForm;
import com.juneng.hellojhworld.repository.BlogPostRepository;
import com.juneng.hellojhworld.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {
    // + @RequiredArgsConstructor 이렇게 생성하는 걸 추천 (테스트하기도 용이)
    private final LikeRepository likeRepository;
    private final BlogPostRepository blogPostRepository;

    // 좋아요 생성
    public void addLike(LikesForm like) {

        // (강사 추천 방법)
        Likes likeE = Likes.createLike(like);
        likeRepository.save(likeE);
        // post 엔티티의 likeCnt 에 +1
        // post 엔티티 조회
        // 이렇게 조회해 온것을 +1 하는게 맞겠지? 확인 필요
        BlogPost blogPost = blogPostRepository.findOne(Long.parseLong(like.getPostId()));
        blogPost.addLike();

    }

    public void cancelLike(LikesForm like) {

        // (강사 추천 방법)
        // postId + userId에 해당하는 좋아요를 찾아서 삭제합니다.
        likeRepository.deleteLikeById(like);
        // post 엔티티의 likeCnt 에 -1
        // post 엔티티 조회
        // 이렇게 조회해 온것을 -1 하는게 맞겠지? 확인 필요
        BlogPost blogPost = blogPostRepository.findOne(Long.parseLong(like.getPostId()));
        blogPost.removeLike();

    }
    @Transactional(readOnly = true)
    public Integer findLikeByUserId(LikesForm like) {

        List<Likes> likes= likeRepository.findLikeByUserId(like);

        return likes.size();
    }

    // 조회 시 (readOnly = true) 로 설정하면 읽기 전용 트렌젝션으로 취급하여 부하를 덜 일으킴.
    @Transactional(readOnly = true)
    public Integer findLikeCntByPostId(String postId) {

        BlogPost blogPost = blogPostRepository.findByPostId(Long.parseLong(postId));

        return blogPost.getLikeCnt();
    }


}
