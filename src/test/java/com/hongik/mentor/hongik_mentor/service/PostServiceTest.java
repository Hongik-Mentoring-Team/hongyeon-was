package com.hongik.mentor.hongik_mentor.service;

import com.hongik.mentor.hongik_mentor.controller.dto.PostCreateDTO;
import com.hongik.mentor.hongik_mentor.controller.dto.PostDTO;
import com.hongik.mentor.hongik_mentor.controller.dto.PostModifyDTO;
import com.hongik.mentor.hongik_mentor.domain.Post;
import com.hongik.mentor.hongik_mentor.domain.PostTag;
import com.hongik.mentor.hongik_mentor.domain.Tag;
import com.hongik.mentor.hongik_mentor.exception.CustomMentorException;
import com.hongik.mentor.hongik_mentor.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    @DisplayName("단일 게시글 조회")
    public void DB식별자기반_게시글_단일_조회(){
        PostDTO post = postService.getPost(1L);

        assertNotNull(post);
        Assertions.assertEquals(post.getPostId(), 1L);
    }

    @Test
    @DisplayName("단일 게시글 수정")
    public void 게시글_수정_성공_테스트(){
        PostModifyDTO postModifyDTO = new PostModifyDTO(1L, "수정 테스트 1",
                "수정 테스트 1", List.of(2L, 3L), null);

        Long postId = postService.modifyPost(postModifyDTO);

        assertNotNull(postId);
        Assertions.assertEquals(postId, 1L);

        PostDTO post = postService.getPost(postId);

        assertNotNull(post);
        Assertions.assertEquals("수정 테스트 1", post.getTitle());
        Assertions.assertEquals("수정 테스트 1", post.getContent());
        Assertions.assertEquals(2L, post.getTags().get(0).getTagId());

    }

    @Test
    @DisplayName("단일 게시글 삭제")
    public void 단일_게시글_삭제_성공(){
        Long postId = postService.deletePost(1L);

        assertNotNull(postId);

        assertThrows(CustomMentorException.class, () -> {
            postService.getPost(postId);
        });

    }
}