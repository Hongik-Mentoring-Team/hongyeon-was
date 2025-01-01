package com.hongik.mentor.hongik_mentor.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PostTag> tags = new ArrayList<>();

    public void addTags(PostTag tag){
        this.tags.add(tag);
    }

}
