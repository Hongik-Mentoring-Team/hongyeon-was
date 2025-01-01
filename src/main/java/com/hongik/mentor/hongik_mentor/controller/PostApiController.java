package com.hongik.mentor.hongik_mentor.controller;

import com.hongik.mentor.hongik_mentor.controller.dto.PostResponseDto;
import com.hongik.mentor.hongik_mentor.controller.dto.PostSaveDto;
import com.hongik.mentor.hongik_mentor.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PostApiController {

    private final PostService postService;

    //게시글 등록
    @PostMapping("/posts")
    public Long createPost(@RequestBody PostSaveDto postSaveDto) {
        return postService.save(postSaveDto);
    }

    //게시글 전체 조회
    @GetMapping("/posts")
    public List<PostResponseDto> findAll() {
        return postService.findAll();
    }

    //게시글 단일 조회
    @GetMapping("/posts/{id}")
    public PostResponseDto findOne(@PathVariable Long id){
        return postService.findOne(id);
    }

    //게시글 수정
    @PutMapping("/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostSaveDto postSaveDto) {
        return postService.updatePost(id, postSaveDto);
    }

    //게시글 삭제
    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    /**
     * 검색
     */

    //게시글 제목으로 검색
    @GetMapping("/search/title")
    public List<PostResponseDto> searchByTitle (@RequestParam String keyword) {
        return postService.searchByTitle(keyword);
    }

    //게시글 내용으로 검색
    @GetMapping("/search/content")
    public List<PostResponseDto> searchByContent (@RequestParam String keyword) {
        return postService.searchByContent(keyword);
    }

    //게시글 작성자로 검색
    @GetMapping("/search/poster")
    public List<PostResponseDto> searchByPoster (@RequestParam String keyword) {
        return postService.searchByPoster(keyword);
    }
}
