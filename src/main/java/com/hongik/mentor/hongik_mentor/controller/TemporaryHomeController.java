package com.hongik.mentor.hongik_mentor.controller;

/* CSR용 가짜 홈컨트롤러*/

import com.hongik.mentor.hongik_mentor.oauth.LoginMember;
import com.hongik.mentor.hongik_mentor.oauth.dto.SessionMember;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemporaryHomeController {
    @GetMapping("/")
    public String home(@LoginMember SessionMember sessionMember) {


        return "home";
    }
}
