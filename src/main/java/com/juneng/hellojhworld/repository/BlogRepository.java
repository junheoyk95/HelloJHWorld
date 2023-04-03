package com.juneng.hellojhworld.repository;

import com.juneng.hellojhworld.domain.BlogPost;
import com.juneng.hellojhworld.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BlogRepository {


    @PersistenceContext
    private EntityManager em;

    public void save(BlogPost blogPost) {
        em.persist(blogPost);
    }

    public List<BlogPost> findAll() {
        return em.createQuery("select p from BlogPost p" , BlogPost.class)
                .getResultList();
    }

    public Page<BlogPost> findPostPaging(Pageable pageable) {
        String jpql = "SELECT b FROM BlogPost b ORDER BY b.id DESC";
        TypedQuery<BlogPost> query = em.createQuery(jpql, BlogPost.class);

        List<BlogPost> resultList = query
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        long totalCount = countTotalPosts();

        return new PageImpl<>(resultList, pageable, totalCount);
    }

    private long countTotalPosts() {
        String jpql = "SELECT COUNT(b) FROM BlogPost b";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        return query.getSingleResult();
    }

    public Page<BlogPost> blogByCategory(Pageable pageable, String category) {
        String jpql = "SELECT b FROM BlogPost b where b.category = :category ORDER BY b.id DESC";
        TypedQuery<BlogPost> query = em.createQuery(jpql, BlogPost.class)
                                        .setParameter("category", category);

        List<BlogPost> resultList = query
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        long totalCount = countTotalPosts();

        return new PageImpl<>(resultList, pageable, totalCount);
    }


    //파라미터 바인딩 아래와 같이
    public  List<BlogPost> findByCategory(String category) {
        return em.createQuery("select p from BlogPost p where p.category = :category", BlogPost.class)
                .setParameter("category", category)
                .getResultList();
    }

    public  List<Map<String, Object>> findPostsCategory() {
        List<Object[]> results =  em.createQuery("select p.category, count(p.id) as postCnt from BlogPost p group by p.category ", Object[].class)
                .getResultList();
        List<Map<String, Object>> mappedResults = new ArrayList<>();
        for (Object[] result : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("category", result[0]);
            map.put("postCnt", result[1]);
            mappedResults.add(map);
        }
        return mappedResults;
    }
}
