package com.hongik.mentor.hongik_mentor.controller;

import com.hongik.mentor.hongik_mentor.controller.dto.MemberRegisterDto;
import com.hongik.mentor.hongik_mentor.controller.dto.MemberResponseDto;
import com.hongik.mentor.hongik_mentor.controller.dto.MemberSaveDto;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.SocialProvider;
import com.hongik.mentor.hongik_mentor.oauth.LoginMember;
import com.hongik.mentor.hongik_mentor.oauth.dto.SessionMember;
import com.hongik.mentor.hongik_mentor.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class MemberApiController {
    private final MemberService memberService;

    //소셜로그인 인증 후 서비스 회원가입
    @PostMapping("/members")
    public Long createMember(@RequestBody MemberRegisterDto memberRegisterDto,
                             @LoginMember SessionMember sessionMember,
                             HttpSession httpSession) {
        //SessionMember업데이트
        sessionMember.update(memberRegisterDto.getName(),
                memberRegisterDto.getMajor(),
                memberRegisterDto.getGraduationYear());
        log.info("세션멤버 업데이트 완료. 세션ID: {}", httpSession.getId());

        MemberSaveDto memberSaveDto = new MemberSaveDto(sessionMember.getSocialId(),
                sessionMember.getProvider(),
                memberRegisterDto.getName(),
                memberRegisterDto.getMajor(),
                memberRegisterDto.getGraduationYear());
        return memberService.save(memberSaveDto);
    }

    //Member 리소스 조회
    @GetMapping("/members")
    public List<MemberResponseDto> findMembers() {
        List<MemberResponseDto> findMembers = memberService.findAll();
        return findMembers;
    }
    @GetMapping("/members/{id}")
    public MemberResponseDto findMember(@PathVariable Long id) {
        return memberService.findById(id);
    }

    @GetMapping("/members/session")
    public Map<String,Object> getSessionMemberInfo(@LoginMember SessionMember sessionMember, HttpSession httpSession) {
        log.info("세션멤버 조회 세션식별자: {}", httpSession.getId());
        return Collections.singletonMap("name",sessionMember.getName());
    }

    @GetMapping("/members/me")
    public MemberResponseDto getMemberProfile(@LoginMember SessionMember sessionMember) {
        MemberResponseDto dto = memberService.findById(sessionMember.getId());
        return dto;
    }

/*  //Member 리소스 수정
    =>필요시 구현
* */


    //Member 리소스 삭제
    @DeleteMapping("/members/me")
    public void deleteMember(@LoginMember SessionMember sessionMember) {
        memberService.delete(sessionMember.getId());
    }



}
