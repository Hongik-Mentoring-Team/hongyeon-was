package com.hongik.mentor.hongik_mentor.controller;

/* SSR용 가짜 홈컨트롤러*/

import com.hongik.mentor.hongik_mentor.controller.dto.MemberSaveDto;
import com.hongik.mentor.hongik_mentor.oauth.LoginMember;
import com.hongik.mentor.hongik_mentor.oauth.dto.SessionMember;
import com.hongik.mentor.hongik_mentor.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Controller
public class LoginController {
    private final MemberService memberService;
    @GetMapping("/")
    public String home(Model model, @LoginMember SessionMember member) {
        if (member != null) {
            model.addAttribute("memberName", member.getName());
        }

        return "home";
    }

    /*회원 정보 추가 입력받기 & db저장*/
    @GetMapping("/member")
    public String createMember(@LoginMember SessionMember member) {
        if (member == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"문제가 발생했습니다. 위치: LoginController");
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
