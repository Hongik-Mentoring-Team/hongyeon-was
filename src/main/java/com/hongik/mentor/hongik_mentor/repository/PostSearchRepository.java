package com.hongik.mentor.hongik_mentor.repository;

import com.hongik.mentor.hongik_mentor.domain.post.Post;

import java.util.List;
import java.util.Optional;

public interface PostSearchRepository {

    List<Post> searchByTags(List<Long> tagIds);
}
