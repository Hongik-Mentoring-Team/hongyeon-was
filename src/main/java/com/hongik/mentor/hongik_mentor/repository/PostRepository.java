package com.hongik.mentor.hongik_mentor.repository;

import com.hongik.mentor.hongik_mentor.domain.Post;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public Post save(Post post){
        em.persist(post);
        return post;
    }

    public Post findOne(Long id) {
        return em.find(Post.class, id);
    }

    public List<Post> findAll() {
        return em.createQuery("select p from Post p", Post.class)
                .getResultList();
    }

    public void delete(Long id){
        Post findPost = findOne(id);
        em.remove(findPost);
    }

    public void deleteAll() {
        em.createQuery("delete from Post")
                .executeUpdate();//delete JPQL이라서 executeUpdate()필요
    }

    public List<Post> searchByTitle(String keyword){
        return em.createQuery("select p from Post p where p.title like concat('%', :keyword, '%')", Post.class)
                .setParameter("keyword", keyword)
                .getResultList();
    }

    public List<Post> searchByContent(String keyword){
        return em.createQuery("select p from Post p where p.content like concat('%', :keyword, '%')", Post.class)
                .setParameter("keyword", keyword)
                .getResultList();
    }

    public List<Post> searchByPoster(String keyword){
        return em.createQuery("select p from Post p where p.poster.name like concat('%', :keyword, '%')", Post.class)
                .setParameter("keyword", keyword)
                .getResultList();
    }
}
