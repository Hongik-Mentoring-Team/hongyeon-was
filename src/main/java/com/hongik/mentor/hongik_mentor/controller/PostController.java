package com.hongik.mentor.hongik_mentor.controller;

import com.hongik.mentor.hongik_mentor.controller.dto.PostCreateDTO;
import com.hongik.mentor.hongik_mentor.controller.dto.PostDTO;
import com.hongik.mentor.hongik_mentor.controller.dto.PostModifyDTO;
import com.hongik.mentor.hongik_mentor.controller.dto.SearchByTagDto;
import com.hongik.mentor.hongik_mentor.domain.Category;
import com.hongik.mentor.hongik_mentor.domain.Tag;
import com.hongik.mentor.hongik_mentor.oauth.util.SessionUtil;
import com.hongik.mentor.hongik_mentor.service.PostService;
import com.hongik.mentor.hongik_mentor.service.dto.PostLikeDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody PostCreateDTO postCreateDTO, HttpSession session) {
        Long memberId = SessionUtil.getCurrentMemberId(session);
        Long postId = postService.createPost(postCreateDTO, memberId);

        return ResponseEntity.status(201).body(postId);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId) {
        PostDTO post = postService.getPost(postId);

        return ResponseEntity.ok(post);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<?> modifyPost(@PathVariable Long postId, @RequestBody PostModifyDTO postModifyDTO) {
        Long modifiedPost = postService.modifyPost(postId, postModifyDTO);

        return ResponseEntity.ok(modifiedPost);
    }

    @DeleteMapping("/post{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/posts/search")
    public ResponseEntity<?> searchPostByTags(@RequestParam(required = false) Category category, @RequestParam(required = false) List<Long> tagIds) {
        if (category == null && tagIds == null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 요청");

        List<PostDTO> postDTOS = postService.searchPostsByTags(category, tagIds);
        return ResponseEntity.ok(postDTOS);
    }

    @PutMapping("/post/{postId}/like")
    public ResponseEntity<?> thumbUpPost(@PathVariable Long postId, @RequestBody PostLikeDTO postLikeDTO) {
        Long likedPostId = postService.thumbUp(postId, postLikeDTO.getMemberId());

        return ResponseEntity.ok(likedPostId);
    }

    /**
     * Tag API
     */

    @GetMapping("/tags")
    public ResponseEntity<?> getTags() {
        List<Tag> tags = postService.getTags();
        log.info(tags.toString());
        return ResponseEntity.ok(tags);
    }
}
