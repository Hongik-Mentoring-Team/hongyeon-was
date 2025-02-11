package com.hongik.mentor.hongik_mentor.service;

import com.hongik.mentor.hongik_mentor.controller.dto.PostCreateDTO;
import com.hongik.mentor.hongik_mentor.controller.dto.PostDTO;
import com.hongik.mentor.hongik_mentor.controller.dto.PostModifyDTO;
import com.hongik.mentor.hongik_mentor.domain.*;
import com.hongik.mentor.hongik_mentor.domain.post.Post;
import com.hongik.mentor.hongik_mentor.domain.post.PostLike;
import com.hongik.mentor.hongik_mentor.domain.post.PostTag;
import com.hongik.mentor.hongik_mentor.domain.post.Tag;
import com.hongik.mentor.hongik_mentor.exception.CustomMentorException;
import com.hongik.mentor.hongik_mentor.exception.ErrorCode;
import com.hongik.mentor.hongik_mentor.repository.MemberRepository;
import com.hongik.mentor.hongik_mentor.repository.PostRepository;
import com.hongik.mentor.hongik_mentor.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    private final MemberRepository memberRepository;

    private final TagRepository tagRepository;

    @Transactional
    public Long createPost(PostCreateDTO postCreateDTO) {

        Member member = memberRepository.findById(postCreateDTO.getMemberId());

        Post post = Post.builder()
                .member(member)
                .title(postCreateDTO.getTitle())
                .content(postCreateDTO.getContent())
                .build();


        postCreateDTO.getTagId()
                .forEach(id -> {
                    Tag tag = tagRepository.findById(id).orElseThrow(() -> new RuntimeException("Tag not found"));
                    PostTag postTag = PostTag.of(tag, post);
                    post.addTags(postTag);
                });

        postRepository.save(post);

        return post.getId();
    }

    public PostDTO getPost(Long postId){

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomMentorException(ErrorCode.POST_NOT_EXISTS));

        return PostDTO.fromPost(post);
    }

    @Transactional
    public Long modifyPost(Long postId, PostModifyDTO postModifyDTO) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomMentorException(ErrorCode.POST_NOT_EXISTS));

        List<Tag> tags = postModifyDTO.getTagIds().stream()
                .map(id -> tagRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Tag not found"))).toList();

        List<PostTag> postTags = tags.stream().map(tag -> PostTag.builder()
                .post(post)
                .tag(tag).build()).toList();

        post.clearTags();

        post.modifyPost(postModifyDTO.getTitle(), postModifyDTO.getContent(), postTags);

        postRepository.save(post);

        return post.getId();

    }

    @Transactional
    public Long deletePost(Long postId){
        postRepository.deleteById(postId);

        return postId;
    }

    public List<PostDTO> searchPostsByTags(List<Long> tagIds){ // 태그들 기반 검색
        List<Post> posts = postRepository.searchByTags(tagIds);

        if (posts.isEmpty()) {
            throw new RuntimeException("Post not found");
        }

        return posts.stream()
                .map(PostDTO::fromPost).toList();
    }

    @Transactional
    public Long thumbUp(Long postId, Long memberId) { // 좋아요 기능
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Member member = memberRepository.findById(memberId);

        addLikesToPost(member, post);

        postRepository.save(post);

        return post.getId();

    }

    private void addLikesToPost(Member member, Post post) {
        PostLike postLike = PostLike.builder()
                .member(member)
                .post(post)
                .build();

        post.addLikes(postLike);
    }

    // 모집 지원 기능
    @Transactional
    public void applyToPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomMentorException(ErrorCode.POST_NOT_EXISTS));

        post.addApplicant();
        postRepository.save(post);
    }

    // 모집 상태 초기화 기능
    @Transactional
    public void resetPostApplicants(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomMentorException(ErrorCode.POST_NOT_EXISTS));

        post.resetApplicants();
        postRepository.save(post);
    }

    // 모집 상태 확인 기능
    public boolean isPostClosed(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomMentorException(ErrorCode.POST_NOT_EXISTS));

        return post.isClosed();
    }

    // 신청 취소 기능
    @Transactional
    public void cancelApplication(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomMentorException(ErrorCode.POST_NOT_EXISTS));

        post.cancelApplicant();
        postRepository.save(post);
    }
}
