package com.hongik.mentor.hongik_mentor.repository;

import com.hongik.mentor.hongik_mentor.domain.post.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
