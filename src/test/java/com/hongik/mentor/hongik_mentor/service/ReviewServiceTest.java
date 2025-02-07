package com.hongik.mentor.hongik_mentor.service;

import com.hongik.mentor.hongik_mentor.controller.dto.MemberResponseDto;
import com.hongik.mentor.hongik_mentor.controller.dto.MemberSaveDto;
import com.hongik.mentor.hongik_mentor.controller.dto.ReviewResponseDto;
import com.hongik.mentor.hongik_mentor.controller.dto.ReviewSaveDto;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.Review;
import com.hongik.mentor.hongik_mentor.domain.SocialProvider;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
class ReviewServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    ReviewService reviewService;

    @Test
    void save() {
        Member member1 = new Member("123", SocialProvider.GOOGLE, "gno", "CS", 2001);
        Member member2 = new Member("5523", SocialProvider.NAVER, "olaf", "CS", 2001);

        Long id1 = memberService.save(new MemberSaveDto(member1.getSocialId(), member1.getSocialProvider(), member1.getName(), member1.getMajor(), member1.getGraduationYear()));
        Long id2 = memberService.save(new MemberSaveDto(member2.getSocialId(), member2.getSocialProvider(), member2.getName(), member2.getMajor(), member2.getGraduationYear()));

        ReviewResponseDto dto = reviewService.save(new ReviewSaveDto("gogogogo bro nice", id1, id2, 3));

        //when
        ReviewResponseDto findReview = reviewService.findById(dto.getId());

        //then
        log.info("review 속 MemberId: {}", findReview.getWriterId());
        Assertions.assertThat(findReview.getWriterId()).isEqualTo(id1);

    }
}