package com.hongik.mentor.hongik_mentor.domain.post;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostTag {

    @Id @GeneratedValue
    @Column(name = "post_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public PostTag(Tag tag, Post post) {
        this.tag = tag;
        this.post = post;
    }

    public static PostTag of(Tag tag, Post post) {
        return PostTag.builder()
                .tag(tag)
                .post(post)
                .build();
    }
}
