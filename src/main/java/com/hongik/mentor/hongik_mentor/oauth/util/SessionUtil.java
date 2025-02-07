package com.hongik.mentor.hongik_mentor.oauth.util;

import com.hongik.mentor.hongik_mentor.oauth.dto.SessionMember;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SessionUtil {
    private final HttpSession httpSession;

    public Long getCurrentMemberId() {
        SessionMember sessionMember = (SessionMember) httpSession.getAttribute("sessionMember");
        return sessionMember.getId();
    }
}
