package com.hongik.mentor.hongik_mentor.service;

import com.hongik.mentor.hongik_mentor.controller.dto.PostCreateDTO;
import com.hongik.mentor.hongik_mentor.controller.dto.PostDTO;
import com.hongik.mentor.hongik_mentor.domain.Post;
import com.hongik.mentor.hongik_mentor.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postService.createPost(new PostCreateDTO("생성 테스트 제목1", "생성 테스트 내용1",
                List.of(1L, 2L), null));

        postService.createPost(new PostCreateDTO("생성 테스트 제목2", "생성 테스트 내용2",
                List.of(1L, 3L), null));

        postService.createPost(new PostCreateDTO("생성 테스트 제목3", "생성 테스트 내용3",
                List.of(3L, 4L), null));
    }

    @Test
    @DisplayName("게시글 생성 테스트")
    public void 게시글_생성_성공_테스트(){
        PostCreateDTO dto = new PostCreateDTO("생성 테스트 제목", "생성 테스트 내용",
                List.of(1L, 2L), null);

        Long postId = postService.createPost(dto);

        assertNotNull(postId);

        Assertions.assertEquals(postId, 1L);
    }

    @Test
    @DisplayName("태그 기반 게시글 조회")
    public void 태그기반_게시글_조회_성공_테스트(){
        List<PostDTO> postDTOS = postService.searchPostsByTags(List.of(1L, 2L));

        assertNotNull(postDTOS);

        Assertions.assertEquals(postDTOS.size(), 1);
        Assertions.assertEquals(postDTOS.get(0).getPostId(), 1L);
    }
}