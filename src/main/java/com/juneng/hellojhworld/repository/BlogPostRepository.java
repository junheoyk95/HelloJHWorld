package com.juneng.hellojhworld.repository;

import com.juneng.hellojhworld.domain.BlogPost;
import com.juneng.hellojhworld.domain.Likes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class BlogPostRepository {

    @PersistenceContext
    private EntityManager em;

    public BlogPost findOne(Long id) {
        return  em.find(BlogPost.class, id);
    }


    //파라미터 바인딩 아래와 같이
    public BlogPost findByPostId(Long postId) {
        return em.createQuery("select p from BlogPost p where p.id = :postId", BlogPost.class)
                .setParameter("postId", postId)
                .getSingleResult();
    }

    public void deletePost(Long postId) {
        BlogPost blogPost = em.find(BlogPost.class, postId);
        em.remove(blogPost);
    }
    public List<BlogPost> findTop3ByOrderByHeartDesc(){
        return em.createQuery("select p from BlogPost p ORDER BY p.likeCnt DESC LIMIT 3", BlogPost.class)
                .getResultList();
    }
}
