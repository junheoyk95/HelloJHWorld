package com.juneng.hellojhworld.repository;

import com.juneng.hellojhworld.domain.BlogPost;
import com.juneng.hellojhworld.domain.Member;
import com.juneng.hellojhworld.dto.MemberForm;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

//    public void save(MemberForm memberForm) {
//        em.persist(memberForm);
//    }

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }


    public Member findByUseIdPw(String userId, String password) {
        return em.createQuery("select m from Member m where m.userId = :userId and m.password = :password", Member.class)
                .setParameter("userId", userId)
                .setParameter("password", password)
                .getSingleResult();
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m" , Member.class)
                .getResultList();
    }

    //파라미터 바인딩 아래와 같이
    public  List<Member> findByName(String userId) {
        return em.createQuery("select m from Member m where m.userId = :userId", Member.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public  List<Member> findByNickname(String nickname) {
        return em.createQuery("select m from Member m where m.nickname = :nickname", Member.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }
}
