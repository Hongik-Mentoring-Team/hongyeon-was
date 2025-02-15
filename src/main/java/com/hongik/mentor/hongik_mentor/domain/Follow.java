package com.hongik.mentor.hongik_mentor.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {

    @Id @GeneratedValue
    @Column(name = "follow_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private Member follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private Member following;

    @Builder
    public Follow(Member follower, Member following) {
        this.follower = follower;
        this.following = following;
    }
}
