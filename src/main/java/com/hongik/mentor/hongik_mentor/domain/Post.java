package com.hongik.mentor.hongik_mentor.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    //글 제목
    @Column(nullable = false)
    private String title;

    //글 내용
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    //작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member poster;

    //댓글
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    //태그
    @Enumerated(EnumType.STRING)
    private HashTag hashTag;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifidedDate;

    public void setPoster(Member member){
        this.poster = member;
        member.getPosts().add(this);
    }


    public Post() {
    }

    public Post(String title, String content, Member poster) {
        this.title = title;
        this.content = content;
        this.poster = poster;
    }

    public void update(String title, String content, Member poster) {
        this.title = title;
        this.content = content;
        this.setPoster(poster);
        this.modifidedDate = LocalDateTime.now();
    }

}
