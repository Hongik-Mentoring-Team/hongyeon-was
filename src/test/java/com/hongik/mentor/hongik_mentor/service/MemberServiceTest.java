package com.hongik.mentor.hongik_mentor.service;

import com.hongik.mentor.hongik_mentor.domain.Badge;
import com.hongik.mentor.hongik_mentor.repository.BadgeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Test
    void 인증메일_전송_테스트() {
        boolean result = memberService.requestUnivEmailVerification("c011204@mail.hongik.ac.kr");
        log.info("결과: {}", result);
    }

    @Test
    void 인증코드_확인_테스트() {
        boolean result = memberService.confirmUnivEmailVerification("c011204@mail.hongik.ac.kr", 2164);
        log.info("인증코드 확인 테스트 결과: {}", result);
    }
}