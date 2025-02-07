package com.hongik.mentor.hongik_mentor.controller;

import com.hongik.mentor.hongik_mentor.controller.dto.FollowRequestDTO;
import com.hongik.mentor.hongik_mentor.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final MemberService memberService;

    @PostMapping("/api/follow")
    public ResponseEntity<?> followMember(@RequestBody FollowRequestDTO followRequestDTO){
        Long followId = memberService.followMember(followRequestDTO);

        return ResponseEntity.ok(followId);

    }

    @DeleteMapping("/api/unfollow/{followId}")
    public ResponseEntity<?> unfollowMember(@PathVariable Long followId){
        memberService.unfollowMember(followId);

        return ResponseEntity.noContent().build();
    }


}
