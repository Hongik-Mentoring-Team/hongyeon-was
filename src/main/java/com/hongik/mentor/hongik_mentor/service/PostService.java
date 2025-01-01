package com.hongik.mentor.hongik_mentor.service;

import com.hongik.mentor.hongik_mentor.controller.dto.PostResponseDto;
import com.hongik.mentor.hongik_mentor.controller.dto.PostSaveDto;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.Post;
import com.hongik.mentor.hongik_mentor.repository.MemberRepository;
import com.hongik.mentor.hongik_mentor.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    //create
    @Transactional
    public Long save(PostSaveDto postSaveDto){
        Member member = memberRepository.findById(postSaveDto.getMemberId());
        if (member == null) {
            throw new IllegalArgumentException("해당 멤버가 존재하지 않습니다. ID: " + postSaveDto.getMemberId());
        }

        Post post = postSaveDto.toEntity(member);
        post.setCreatedDate(LocalDateTime.now());
        post.setModifidedDate(LocalDateTime.now());

        Post savedPost = postRepository.save(post);

        return savedPost.getId();
    }

    //read
    public PostResponseDto findOne(Long id){
        Post post = postRepository.findOne(id);

        if (post == null) {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다. ID: " + id);
        }

        return new PostResponseDto(post);
    }

    public List<PostResponseDto> findAll(){
        List<Post> postList = postRepository.findAll();

        return postList.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    //update
    @Transactional
    public Long updatePost(Long postId, PostSaveDto postSaveDto) {
        Post post = postRepository.findOne(postId);
        if (post == null) {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다. ID: " + postId);
        }

        Member member = memberRepository.findById(postSaveDto.getMemberId());
        if (member == null) {
            throw new IllegalArgumentException("해당 멤버가 존재하지 않습니다. ID: " + postSaveDto.getMemberId());
        }

        post.update(postSaveDto.getTitle(), postSaveDto.getContent(), member);

        return post.getId();
    }

    //delete
    @Transactional
    public void deletePost(Long id) {
        postRepository.delete(id);
    }

    //게시글 제목으로 검색
    public List<PostResponseDto> searchByTitle(String keyword) {
        List<Post> posts = postRepository.searchByTitle(keyword);
        return posts.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    //게시글 내용으로 검색
    public List<PostResponseDto> searchByContent(String keyword) {
        List<Post> posts = postRepository.searchByContent(keyword);
        return posts.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }


    //게시글 작성자로 검색
    public List<PostResponseDto> searchByPoster(String keyword) {
        List<Post> posts = postRepository.searchByPoster(keyword);
        return posts.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }
}
