package com.hongik.mentor.hongik_mentor.service;

import com.hongik.mentor.hongik_mentor.controller.dto.CommentCreateDto;
import com.hongik.mentor.hongik_mentor.controller.dto.CreatedCommentDto;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.post.Comment;
import com.hongik.mentor.hongik_mentor.domain.post.Post;
import com.hongik.mentor.hongik_mentor.exception.CustomMentorException;
import com.hongik.mentor.hongik_mentor.exception.ErrorCode;
import com.hongik.mentor.hongik_mentor.repository.CommentRepository;
import com.hongik.mentor.hongik_mentor.repository.MemberRepository;
import com.hongik.mentor.hongik_mentor.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;

    private final MemberRepository memberRepository;

    private final PostRepository postRepository;

    @Transactional
    public CreatedCommentDto createComment(CommentCreateDto request) {

        Member member = memberRepository.findById(request.getMemberId());

        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new CustomMentorException(ErrorCode.POST_NOT_EXISTS));

        Comment comment = Comment.of(request, member, post);

        commentRepository.save(comment);



        return new CreatedCommentDto(comment.getId());
    }


}
