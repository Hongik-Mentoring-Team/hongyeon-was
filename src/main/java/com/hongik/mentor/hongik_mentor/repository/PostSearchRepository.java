package com.hongik.mentor.hongik_mentor.repository;

import com.hongik.mentor.hongik_mentor.domain.Post;

import java.util.List;

public interface PostSearchRepository {

    List<Post> searchByTags(List<Long> tagIds);
}
