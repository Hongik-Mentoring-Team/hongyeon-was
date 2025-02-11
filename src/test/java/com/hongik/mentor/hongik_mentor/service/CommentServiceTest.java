package com.hongik.mentor.hongik_mentor.service;

import com.hongik.mentor.hongik_mentor.controller.dto.CommentCreateDto;
import com.hongik.mentor.hongik_mentor.controller.dto.CreatedCommentDto;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.SocialProvider;
import com.hongik.mentor.hongik_mentor.domain.post.Post;
import com.hongik.mentor.hongik_mentor.repository.MemberRepository;
import com.hongik.mentor.hongik_mentor.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentServiceTest {


    @Autowired
    private PostService postService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentService commentService;

    @DisplayName("게시글에 댓글을 작성한다.")
    @Test
    void createComment(){

        //given
        Member member = new Member("socialId", SocialProvider.KAKAO, "박승범", "컴퓨터공학과", 2025);

        memberRepository.save(member);

        Post post = Post.builder()
                .title("게시글1")
                .content("내용1")
                .member(member)
                .build();


        postRepository.save(post);

        CommentCreateDto request = new CommentCreateDto("댓글1", member.getId(), post.getId());

        //when
        CreatedCommentDto createdCommentDto = commentService.createComment(request);

        //then
        Assertions.assertThat(createdCommentDto.getCommentId()).isNotNull();

    }

    @DisplayName("게시글의 댓글을 수정한다.")
    @Test
    void modifyComment(){

        //given

        //when

        //then

    }

    @DisplayName("")
    @Test
    void deleteComment(){

        //given

        //when

        //then

    }

}