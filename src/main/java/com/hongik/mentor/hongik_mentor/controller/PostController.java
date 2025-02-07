package com.hongik.mentor.hongik_mentor.controller;

import com.hongik.mentor.hongik_mentor.controller.dto.PostCreateDTO;
import com.hongik.mentor.hongik_mentor.controller.dto.PostDTO;
import com.hongik.mentor.hongik_mentor.controller.dto.PostModifyDTO;
import com.hongik.mentor.hongik_mentor.controller.dto.SearchByTagDto;
import com.hongik.mentor.hongik_mentor.service.PostService;
import com.hongik.mentor.hongik_mentor.service.dto.PostLikeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/api/post")
    public ResponseEntity<?> createPost(@RequestBody PostCreateDTO postCreateDTO) {

        Long postId = postService.createPost(postCreateDTO);

        return ResponseEntity.status(201).body(postId);
    }

    @GetMapping("/api/post/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId) {
        PostDTO post = postService.getPost(postId);

        return ResponseEntity.ok(post);
    }

    @PutMapping("/api/post/{postId}")
    public ResponseEntity<?> modifyPost(@PathVariable Long postId, @RequestBody PostModifyDTO postModifyDTO) {
        Long modifiedPost = postService.modifyPost(postId, postModifyDTO);

        return ResponseEntity.ok(modifiedPost);
    }

    @DeleteMapping("/api/post{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/posts/search")
    public ResponseEntity<?> searchPostByTags(@RequestBody SearchByTagDto searchByTagDto) {
        List<PostDTO> postDTOS = postService.searchPostsByTags(searchByTagDto.getTagIds());

        return ResponseEntity.ok(postDTOS);
    }

    @PutMapping("/api/post/{postId}/like")
    public ResponseEntity<?> thumbUpPost(@PathVariable Long postId, @RequestBody PostLikeDTO postLikeDTO) {
        Long likedPostId = postService.thumbUp(postId, postLikeDTO.getMemberId());

        return ResponseEntity.ok(likedPostId);
    }
}
