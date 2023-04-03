package com.juneng.hellojhworld.repository;

import com.juneng.hellojhworld.domain.BlogPost;
import com.juneng.hellojhworld.domain.Comment;
import com.juneng.hellojhworld.dto.CommentForm;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Comment comment) {
        em.persist(comment);
    }


    //파라미터 바인딩 아래와 같이
    public  List<Comment> findCommentByPostId(String postId) {
        return em.createQuery("select c from Comment c where c.blogPost.id = :postId " +
                        "ORDER BY CASE WHEN c.commentType = '1' THEN c.id " +
                        "WHEN c.commentType = '2' THEN c.parentId END, c.registDt", Comment.class)
                .setParameter("postId", postId)
                .getResultList();
    }
}
