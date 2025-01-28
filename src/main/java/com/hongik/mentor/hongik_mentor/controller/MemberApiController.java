package com.hongik.mentor.hongik_mentor.controller;

import com.hongik.mentor.hongik_mentor.controller.dto.MemberResponseDto;
import com.hongik.mentor.hongik_mentor.controller.dto.MemberSaveDto;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class MemberApiController {
    private final MemberService memberService;

    //Member 리소스 등록
    @PostMapping("/members")
    public Long createMember(@RequestBody MemberSaveDto memberSaveDto) {
        return memberService.save(memberSaveDto);
    }

    //Member 리소스 조회
    @GetMapping("/members")
    public List<MemberResponseDto> findMembers() {
        List<MemberResponseDto> findMembers = memberService.findAll();
        return findMembers;
    }

    //Member 단건 조회
    @GetMapping("/members/{id}")
    public MemberResponseDto findMember(@PathVariable Long id) {
        return memberService.findById(id);
    }

/*  //Member 리소스 수정
    =>필요시 구현
* */


    //Member 리소스 삭제
    @DeleteMapping("/members/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.delete(id);
    }

    //Member리소스 로그인
    @PostMapping("/login")
    public void sendAuthorizationCode(/*@RequestBody ??? */) {

    }

}
