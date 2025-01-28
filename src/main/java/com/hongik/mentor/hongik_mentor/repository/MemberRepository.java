package com.hongik.mentor.hongik_mentor.repository;

import com.hongik.mentor.hongik_mentor.controller.dto.MemberResponseDto;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.MemberType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.NonUniqueResultException;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    //Create
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    //Read
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }

    public List<Member> findAll() {     //Member가 없을 경우 빈 리스트 반환
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    //Delete
    public void delete(Long id) {
        Member findMember = em.find(Member.class, id);
        em.remove(findMember);
    }

    public void deleteAll() {
        em.createQuery("delete from Member")
                .executeUpdate();//delete JPQL이라서 executeUpdate()필요
    }

    public Optional<Member> findBySocialId(String userNameAttributeName) {
        //getSingleResult()는 조회 대상이 없을 경우 예외발생시킴
        Optional<Member> findMember = em.createQuery("select m from Member m where m.socialId = :userNameAttributeName", Member.class)
                .setParameter("userNameAttributeName", userNameAttributeName)
                .getResultStream().findFirst(); //조회 결과: 1명 조회 | 0명 조회
        return findMember;

    }
}
