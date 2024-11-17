package com.hongik.mentor.hongik_mentor.repository;

import com.hongik.mentor.hongik_mentor.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    //Create
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    //Read
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {     //Member가 없을 경우 빈 리스트 반환
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    //Update

    //Delete
    public void delete(Long id) {
        Member findMember = em.find(Member.class, id);
        em.remove(findMember);
    }

    public void deleteAll() {
        em.createQuery("delete from Member")
                .executeUpdate();//delete JPQL이라서 executeUpdate()필요
    }
}
