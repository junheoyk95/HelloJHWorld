package com.juneng.hellojhworld.repository;

import com.juneng.hellojhworld.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LoginRepository {

    @PersistenceContext
    private EntityManager em;


    public Optional<Member> findByUseIdPw(String userId, String password) {
        try {
            Member member = em.createQuery("select m from Member m where m.userId = :userId and m.password = :password", Member.class)
                    .setParameter("userId", userId)
                    .setParameter("password", password)
                    .getSingleResult();

            return Optional.of(member);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
