package com.hongik.mentor.hongik_mentor.service;

import com.hongik.mentor.hongik_mentor.controller.dto.PostCreateDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    @DisplayName("게시글 생성 테스트")
    public void 게시글_생성_성공_테스트(){
        PostCreateDTO dto = new PostCreateDTO("생성 테스트 제목", "생성 테스트 내용",
                List.of(1L, 2L), null);

        Long postId = postService.createPost(dto);

        assertNotNull(postId);

        Assertions.assertEquals(postId, 1L);
    }
}