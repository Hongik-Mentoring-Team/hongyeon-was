package com.hongik.mentor.hongik_mentor.service;


import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.SocialProvider;
import com.hongik.mentor.hongik_mentor.repository.FollowRepository;
import com.hongik.mentor.hongik_mentor.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FollowServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private FollowRepository followRepository;

    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("회원 팔로우에 성공한다.")
    public void follow_member_success(){
        Long followerId = 1L;

        Long followeeId = 2L;


    }
}
