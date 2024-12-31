package com.hongik.mentor.hongik_mentor.service;

import com.hongik.mentor.hongik_mentor.controller.dto.PostCreateDTO;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.Post;
import com.hongik.mentor.hongik_mentor.domain.Tag;
import com.hongik.mentor.hongik_mentor.repository.MemberRepository;
import com.hongik.mentor.hongik_mentor.repository.PostRepository;
import com.hongik.mentor.hongik_mentor.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    private final MemberRepository memberRepository;

    private final TagRepository tagRepository;

    @Transactional
    public Long createPost(PostCreateDTO postCreateDTO) {

//        Member member = memberRepository.findById(postCreateDTO.getMemberId());

        Post post = Post.builder()
//                .member(member)
                .title(postCreateDTO.getTitle())
                .content(postCreateDTO.getContent())
                .build();


        postCreateDTO.getTagId().stream()
                .forEach(id -> {
                    Tag tag = tagRepository.findById(id).orElseThrow(() -> new RuntimeException("Tag not found"));
                    post.addTags(tag);
                });

        postRepository.save(post);

        return post.getId();
    }

}
