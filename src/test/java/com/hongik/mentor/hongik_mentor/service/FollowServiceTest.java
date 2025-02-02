package com.hongik.mentor.hongik_mentor.service;


import com.hongik.mentor.hongik_mentor.controller.dto.FollowRequestDTO;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.SocialProvider;
import com.hongik.mentor.hongik_mentor.repository.FollowRepository;
import com.hongik.mentor.hongik_mentor.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class FollowServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    @DisplayName("회원 팔로우에 성공한다.")
    @Transactional
    public void follow_member_success() {
        //given
        Member member1 = new Member("1111", SocialProvider.GOOGLE, "박승범", "컴퓨터공학과", 2025);

        Member member2 = new Member("2222", SocialProvider.GOOGLE, "최근호", "컴퓨터공학과", 2025);

        memberRepository.save(member1);
        memberRepository.save(member2);

        FollowRequestDTO request = FollowRequestDTO.builder()
                .followerId(member1.getId())
                .followeeId(member2.getId())
                .build();

        //when

        Long followId = memberService.followMember(request);

        //then

        assertThat(followId).isEqualTo(1L);


    }


}