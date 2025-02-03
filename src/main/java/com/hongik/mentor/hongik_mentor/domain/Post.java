package com.hongik.mentor.hongik_mentor.domain;

import com.hongik.mentor.hongik_mentor.controller.dto.PostCreateDTO;
import com.hongik.mentor.hongik_mentor.controller.dto.PostModifyDTO;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PostTag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PostLike> likes = new ArrayList<>();

    //모집인원
    private int capacity;

    //현재 신청자 수
    @Builder.Default
    private int currentApplicants = 0;

    //모집 마감 여부
    @Builder.Default
    private boolean isClosed = false;

    public void addTags(PostTag tag){
        this.tags.add(tag);
    }

    public void addLikes(PostLike like){
        this.likes.add(like);
    }

    public void clearTags(){
        this.tags.clear();
    }

    public void modifyPost(String title, String content, List<PostTag> postTags) {
        this.title = title;
        this.content = content;
        this.tags.addAll(postTags);
    }

    //신청자 멤버 리스트
    @ManyToMany
    @JoinTable(
            name = "post_applicants",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    @Builder.Default
    private List<Member> applicants = new ArrayList<>();


    //신청자 추가
    public void addApplicant(Member member) {
        if (isClosed) {
            throw new IllegalStateException("모집이 이미 마감되었습니다.");
        }
        if (this.applicants.contains(member)) {
            throw new IllegalStateException("이미 신청한 회원입니다.");
        }

        this.currentApplicants++;
        this.applicants.add(member);
        if (this.currentApplicants >= this.capacity) {
            this.isClosed = true;
        }
    }

    //신청 취소
    public void cancelApplicant(Member member) {
        if (!this.applicants.contains(member)) {
            throw new IllegalStateException("신청한 적이 없는 회원입니다.");
        }
        this.currentApplicants--;
        this.applicants.remove(member);
        if (this.isClosed) {
            this.isClosed = false;
        }
    }

    //모집 상태 초기화
    public void resetApplicants() {
        this.currentApplicants = 0;
        this.isClosed = false;
        this.applicants.clear();
    }
}
