package com.hongik.mentor.hongik_mentor.repository;

import com.hongik.mentor.hongik_mentor.domain.Comment;
import com.hongik.mentor.hongik_mentor.domain.Post;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public Comment save(Comment comment){
        em.persist(comment);
        return comment;
    }

    public Comment findOne(Long id) {
        return em.find(Comment.class, id);
    }

    public List<Comment> findCommentsByPostId(Long id){
        return em.createQuery("select c from Comment c where c.post.id = :id", Comment.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c", Comment.class)
                .getResultList();
    }

    public void delete(Long id){
        Comment findComment = findOne(id);
        em.remove(findComment);
    }

    public void deleteAll() {
        em.createQuery("delete from Comment")
                .executeUpdate();//delete JPQL이라서 executeUpdate()필요
    }

    public List<Comment> searchByComment(String keyword){
        return em.createQuery("select c from Comment c where c.content like concat('%', :keyword, '%')", Comment.class)
                .setParameter("keyword", keyword)
                .getResultList();
    }
}
