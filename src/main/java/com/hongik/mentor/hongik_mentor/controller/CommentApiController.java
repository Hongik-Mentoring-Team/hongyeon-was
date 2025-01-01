package com.hongik.mentor.hongik_mentor.controller;

import com.hongik.mentor.hongik_mentor.controller.dto.CommentResponseDto;
import com.hongik.mentor.hongik_mentor.controller.dto.CommentSaveDto;
import com.hongik.mentor.hongik_mentor.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommentApiController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/comments")
    public Long createComment(@RequestBody CommentSaveDto commentSaveDto) {
        return commentService.saveComment(commentSaveDto);
    }

    // 특정 게시글의 모든 댓글 조회
    @GetMapping("/comments/{Id}")
    public List<CommentResponseDto> getCommentsByPostId(@PathVariable Long Id) {
        return commentService.findCommentsByPostId(Id);
    }

    // 댓글 수정
    @PutMapping("/comments/{id}")
    public void updateComment(@PathVariable Long id, @RequestBody String newContent) {
        commentService.updateComment(id, newContent);
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }

    // 댓글 검색
    @GetMapping("/search/comments")
    public List<CommentResponseDto> searchComments(@RequestParam String keyword) {
        return commentService.searchByComment(keyword);
    }
}
