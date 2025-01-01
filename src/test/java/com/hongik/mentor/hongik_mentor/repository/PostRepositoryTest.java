package com.hongik.mentor.hongik_mentor.repository;

import com.hongik.mentor.hongik_mentor.domain.*;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void findOneTest() {
        //given
        Member member = new Member("1", SocialProvider.GOOGLE, "Lee", "CS", 2026);

        Post post = new Post();
        post.setTitle("hello");
        post.setContent("홍익 대학교");
        post.setPoster(member);
        postRepository.save(post);

        //when
        Post findPost = postRepository.findOne(post.getId());

        //then
        assertThat(findPost.getId()).isEqualTo(post.getId());
    }

    @Test
    public void findAllTest(){

        //given
        Member member = new Member("1", SocialProvider.GOOGLE, "Lee", "CS", 2026);
        memberRepository.save(member);

        Post post1 = new Post("hello", "홍익 대학교", member);
        Post post2 = new Post("hello", "홍익 대학교", member);
        Post post3 = new Post("hello", "홍익 대학교", member);

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);

        //when
        List<Post> postList = postRepository.findAll();

        //then
        assertThat(postList.size()).isEqualTo(3);
    }

    @Test
    public void deleteTest(){

        //given
        Member member = new Member("1", SocialProvider.GOOGLE, "Lee", "CS", 2026);
        memberRepository.save(member);

        Post post1 = new Post("hello", "홍익 대학교", member);
        Post post2 = new Post("hello", "홍익 대학교", member);
        Post post3 = new Post("hello", "홍익 대학교", member);

        Post savedPost1 = postRepository.save(post1);
        Post savedPost2 = postRepository.save(post2);
        Post savedPost3 = postRepository.save(post3);

        //when
        postRepository.delete(savedPost1.getId());
        postRepository.delete(savedPost2.getId());
        postRepository.delete(savedPost3.getId());
        List<Post> postList = postRepository.findAll();

        //then
        assertThat(postList).isEmpty();
    }

    @Test
    public void deleteAllTest(){

        //given
        Member member = new Member("1", SocialProvider.GOOGLE, "Lee", "CS", 2026);
        memberRepository.save(member);

        Post post1 = new Post("hello", "홍익 대학교", member);
        Post post2 = new Post("hello", "홍익 대학교", member);
        Post post3 = new Post("hello", "홍익 대학교", member);

        Post savedPost1 = postRepository.save(post1);
        Post savedPost2 = postRepository.save(post2);
        Post savedPost3 = postRepository.save(post3);

        //when
        postRepository.deleteAll();
        List<Post> postList = postRepository.findAll();

        //then
        assertThat(postList).isEmpty();
    }

    @Test
    public void searchByTitleTest(){
        //given
        Member member = new Member("1", SocialProvider.GOOGLE, "Lee", "CS", 2026);
        memberRepository.save(member);

        Post post1 = new Post("홍익 대학교", "홍익 대학교", member);
        Post post2 = new Post("홍익대학교 컴퓨터공학과", "홍익대학교 컴퓨터공학과", member);
        Post post3 = new Post("안녕하세요", "안녕하세요", member);
        Post post4 = new Post("홍 익 대학교 학생", "홍 익 대학교 학생", member);

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        postRepository.save(post4);

        //when
        List<Post> result = postRepository.searchByTitle("홍익");

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void searchByContentTest(){
        //given
        Member member = new Member("1", SocialProvider.GOOGLE, "Lee", "CS", 2026);
        memberRepository.save(member);

        Post post1 = new Post("제목", "홍익 대학교", member);
        Post post2 = new Post("제목", "홍익대학교 컴퓨터공학과", member);
        Post post3 = new Post("제목", "안녕하세요", member);
        Post post4 = new Post("제목", "홍 익 대학교 학생", member);

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        postRepository.save(post4);

        //when
        List<Post> result = postRepository.searchByContent("홍익");

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void searchByPosterTest(){
        //given
        Member member = new Member("1", SocialProvider.GOOGLE, "Lee", "CS", 2026);
        memberRepository.save(member);

        Member member2 = new Member("1", SocialProvider.GOOGLE, "Kim", "CS", 2026);
        memberRepository.save(member2);

        Post post1 = new Post("제목", "홍익 대학교", member);
        Post post2 = new Post("제목", "홍익대학교 컴퓨터공학과", member);
        Post post3 = new Post("제목", "안녕하세요", member);
        Post post4 = new Post("제목", "홍 익 대학교 학생", member2);

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        postRepository.save(post4);

        //when
        List<Post> result = postRepository.searchByPoster("Lee");

        //then
        Assertions.assertThat(result.size()).isEqualTo(3);
    }
}