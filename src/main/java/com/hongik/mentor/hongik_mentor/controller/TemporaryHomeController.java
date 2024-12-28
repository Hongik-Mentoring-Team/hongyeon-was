package com.hongik.mentor.hongik_mentor.controller;

/* SSR용 가짜 홈컨트롤러*/

import com.hongik.mentor.hongik_mentor.controller.dto.MemberSaveDto;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.oauth.LoginMember;
import com.hongik.mentor.hongik_mentor.oauth.dto.SessionMember;
import com.hongik.mentor.hongik_mentor.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

import java.sql.Timestamp;

//@LoginMember SessionMember sessionMember
@RequiredArgsConstructor
@Controller
public class TemporaryHomeController {
    private final MemberService memberService;
    @GetMapping("/")
    public String home(Model model, @LoginMember SessionMember member) {
        if (member != null) {
            model.addAttribute("memberName", member.getName());
        }

        return "home";
    }

    //회원 추가 정보 입력받기
    @GetMapping("/member")
    public String createMember(@LoginMember SessionMember member) {
        if (member == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"문제가 발생했습니다. 위치: TemporaryHomeController");
        return "createMember";
    }

    @PostMapping("/member")
    public String saveMember(@RequestParam("memberName") String name, @RequestParam String major, @RequestParam Integer graduationYear
            , @LoginMember SessionMember sessionMember) {
        sessionMember.update(name, major, graduationYear);
        memberService.save(new MemberSaveDto(sessionMember.getSocialId(), sessionMember.getProvider(), name, major, graduationYear));

        return "redirect:/";
    }
}
