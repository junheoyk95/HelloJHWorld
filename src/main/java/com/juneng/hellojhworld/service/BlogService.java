package com.juneng.hellojhworld.service;

import com.juneng.hellojhworld.domain.BlogPost;
import com.juneng.hellojhworld.domain.Member;
import com.juneng.hellojhworld.repository.BlogRepository;
import com.juneng.hellojhworld.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

@Service
@Transactional // 데이터 변경이 일어나야하므로 @트렌젝션 필수!!
@RequiredArgsConstructor
public class BlogService {
    // + @RequiredArgsConstructor 이렇게 생성하는 걸 추천 (테스트하기도 용이)
    private final BlogRepository blogRepository;

    // post 생성
    public Long postCreate(BlogPost blogPost) {

        blogRepository.save(blogPost);

        return blogPost.getId();
    }

    // 조회 시 (readOnly = true) 로 설정하면 읽기 전용 트렌젝션으로 취급하여 부하를 덜 일으킴.
    @Transactional(readOnly = true)
    public List<BlogPost> findBlogPosts() {
        return blogRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<BlogPost> findByCategory(String category) {
        return blogRepository.findByCategory(category);
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> findPostsCategory() {
        return blogRepository.findPostsCategory();
    }

    /* Paging */
    @Transactional(readOnly = true)
    public Page<BlogPost> pageList(int page) {
        return blogRepository.findPostPaging(PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id")));
    }

    @Transactional(readOnly = true)
    public Page<BlogPost> blogByCategory(int page, String category) {
        return blogRepository.blogByCategory(PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id")), category);
    }
}
