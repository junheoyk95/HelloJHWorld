package com.juneng.hellojhworld.service;

import com.juneng.hellojhworld.domain.BlogPost;
import com.juneng.hellojhworld.repository.BlogPostRepository;
import com.juneng.hellojhworld.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional // 데이터 변경이 일어나야하므로 @트렌젝션 필수!!
@RequiredArgsConstructor
public class BlogPostService {
    // + @RequiredArgsConstructor 이렇게 생성하는 걸 추천 (테스트하기도 용이)
    private final BlogPostRepository blogPostRepository;


    // 조회 시 (readOnly = true) 로 설정하면 읽기 전용 트렌젝션으로 취급하여 부하를 덜 일으킴.
    @Transactional(readOnly = true)
    public BlogPost findBlogPost(Long postId) {

        return blogPostRepository.findByPostId(postId);
    }

    public void deletePost(Long postId) {

        blogPostRepository.deletePost(postId);
    }

    public List<BlogPost> findTop3BlogPostsOrderByHeartDesc() {
        return blogPostRepository.findTop3ByOrderByHeartDesc();
    }


}
