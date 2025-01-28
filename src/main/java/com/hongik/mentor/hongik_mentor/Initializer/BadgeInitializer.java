package com.hongik.mentor.hongik_mentor.Initializer;

import com.hongik.mentor.hongik_mentor.domain.Badge;
import com.hongik.mentor.hongik_mentor.service.BadgeService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
//@Component    //main어플리케이션 클래스에서 수동 빈 등록
public class BadgeInitializer {
    private final BadgeService badgeService;

    @PostConstruct
    public void initBadge() {
        if (badgeService.count() == 0) {
            Badge badge1 = new Badge("badge1", "exampleUrl1");  //실제 이미지 파일 필요
            Badge badge2 = new Badge("badge2", "exampleUrl2");

            badgeService.addBadge(badge1);
            badgeService.addBadge(badge2);
        }
    }
}
