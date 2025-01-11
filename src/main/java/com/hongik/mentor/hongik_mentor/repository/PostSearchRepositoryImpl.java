package com.hongik.mentor.hongik_mentor.repository;

import com.hongik.mentor.hongik_mentor.domain.Post;
import com.hongik.mentor.hongik_mentor.domain.QPost;
import com.hongik.mentor.hongik_mentor.domain.QPostTag;
import com.hongik.mentor.hongik_mentor.domain.QTag;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

public class PostSearchRepositoryImpl implements PostSearchRepository{

    private final JPAQueryFactory queryFactory;

    public PostSearchRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Optional<Post> getPostById(Long id) {
        QPost p = QPost.post;

        Post post = queryFactory
                .select(p)
                .from(p)
                .where(p.id.eq(id))
                .fetchOne();

        return Optional.ofNullable(post);
    }

    @Override
    public List<Post> searchByTags(List<Long> tagIds) {
        QPost p = QPost.post;
        QTag t = QTag.tag;
        QPostTag pt = QPostTag.postTag;

        List<Post> posts = queryFactory
                .selectFrom(p)
                .where(p.id.in(
                        JPAExpressions
                                .select(pt.post.id)
                                .from(pt)
                                .where(pt.tag.id.in(tagIds))
                                .groupBy(pt.post.id)
                                .having(pt.tag.id.count().eq((long) tagIds.size()))
                ))
                .fetch();

        return posts;
    }
}
