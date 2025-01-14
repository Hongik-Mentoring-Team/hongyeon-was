package com.hongik.mentor.hongik_mentor.repository;

import com.hongik.mentor.hongik_mentor.domain.Badge;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BadgeRepository {
    @PersistenceContext
    private final EntityManager em;
    //create
    public void save(Badge badge) {
        em.persist(badge);
    }

    //read
    public Badge findById(Long id) {
        Badge findBadge = em.find(Badge.class, id);
        return findBadge;
    }

    public List<Badge> findAll() {
        List badges = em.createQuery("select b from Badge b")
                .getResultList();
        return badges;
    }
    //update

    //delete
    public void delete(Long id) {
        Badge findBadge = em.find(Badge.class, id);
        em.remove(findBadge);
    }
}
