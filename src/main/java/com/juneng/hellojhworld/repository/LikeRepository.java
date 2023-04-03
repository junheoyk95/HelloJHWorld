package com.juneng.hellojhworld.repository;

import com.juneng.hellojhworld.domain.Likes;
import com.juneng.hellojhworld.dto.LikesForm;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LikeRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Likes like) {
        em.persist(like);
    }

    public void deleteLikeById(LikesForm like) {
        int deletedCount = em.createQuery("DELETE FROM Likes l WHERE l.postId = :postId AND l.userId = :userId")
                .setParameter("postId", like.getPostId())
                .setParameter("userId", like.getUserId())
                .executeUpdate();
    }


    //파라미터 바인딩 아래와 같이
    public  List<Likes> findLikeByPostId(String postId) {
        return em.createQuery("select c from Comment c where c.blogPost.id = :postId " +
                        "ORDER BY CASE WHEN c.commentType = '1' THEN c.id " +
                        "WHEN c.commentType = '2' THEN c.parentId END, c.registDt", Likes.class)
                .setParameter("postId", postId)
                .getResultList();
    }

    public  List<Likes> findLikeByUserId(LikesForm like) {
        return em.createQuery("select l from Likes l where l.blogPost.id = :postId AND l.userId = :userId", Likes.class)
                .setParameter("postId", like.getPostId())
                .setParameter("userId", like.getUserId())
                .getResultList();
    }

}
