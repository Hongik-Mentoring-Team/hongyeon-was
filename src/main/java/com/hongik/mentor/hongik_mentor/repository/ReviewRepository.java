package com.hongik.mentor.hongik_mentor.repository;

import com.hongik.mentor.hongik_mentor.domain.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepository {

    @PersistenceContext
    private EntityManager em;

    //저장
    public Review save(Review review){
        em.persist(review);
        return review;
    }

    //단일 조회
    public Review findById(Long id){
        return em.find(Review.class, id);
    }

    //전체 조회
    public List<Review> findAll(){
        return em.createQuery("select r from Review r", Review.class)
                .getResultList();
    }

    //단일 삭제
    public void delete(Long id) {
        Review findReview = em.find(Review.class, id);
        em.remove(findReview);
    }

    //전체 삭제
    public void deleteAll() {
        em.createQuery("delete from Review")
                .executeUpdate();
    }


}
