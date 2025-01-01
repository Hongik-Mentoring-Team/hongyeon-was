package com.hongik.mentor.hongik_mentor.service;

import com.hongik.mentor.hongik_mentor.controller.dto.CommentResponseDto;
import com.hongik.mentor.hongik_mentor.controller.dto.CommentSaveDto;
import com.hongik.mentor.hongik_mentor.domain.Comment;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.Post;
import com.hongik.mentor.hongik_mentor.repository.CommentRepository;
import com.hongik.mentor.hongik_mentor.repository.MemberRepository;
import com.hongik.mentor.hongik_mentor.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    // 댓글 생성
    public Long saveComment(CommentSaveDto commentSaveDto) {
        System.out.println("commenter id = " + commentSaveDto.getCommenterId());
        Member member = memberRepository.findById(commentSaveDto.getCommenterId());
        if (member == null) {
            throw new IllegalArgumentException("존재하지 않는 멤버입니다. ID: " + commentSaveDto.getCommenterId());
        }

        Post post = postRepository.findOne(commentSaveDto.getPostId());
        if (post == null) {
            throw new IllegalArgumentException("존재하지 않는 게시글입니다. ID: " + commentSaveDto.getPostId());
        }

        Comment comment = commentSaveDto.toEntity(member, post);

        if (comment.getId() == null) System.out.println("id가 null임");
        else System.out.println("id = " + comment.getId());

        commentRepository.save(comment);
        return comment.getId();
    }

    // 댓글 조회
    @Transactional(readOnly = true)
    public List<CommentResponseDto> findCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findCommentsByPostId(postId);

        return comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findOne(commentId);
        if (comment == null) {
            throw new IllegalArgumentException("존재하지 않는 댓글입니다. ID: " + commentId);
        }
        commentRepository.delete(commentId);
    }

    // 댓글 수정
    public void updateComment(Long commentId, String newContent) {
        Comment comment = commentRepository.findOne(commentId);
        if (comment == null) {
            throw new IllegalArgumentException("존재하지 않는 댓글입니다. ID: " + commentId);
        }

        // 댓글 업데이트
        comment.updateContent(newContent);
    }

    // 댓글 내용 검색
    public List<CommentResponseDto> searchByComment(String keyword) {
        List<Comment> comments = commentRepository.searchByComment(keyword);

        return comments.stream()
                .map(CommentResponseDto::new)
                .toList();
    }
}
