package com.hongik.mentor.hongik_mentor.controller.dto;

import com.hongik.mentor.hongik_mentor.domain.Review;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {

    private final Long id;
    private final String content;
    private final Long writerId;
    private final Long targetId;
    private final int rating;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ReviewResponseDto(Long id, String content, Long writerId, Long targetId, int rating, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.content = content;
        this.writerId = writerId;
        this.targetId = targetId;
        this.rating = rating;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ReviewResponseDto fromEntity(Review review) {
        return new ReviewResponseDto(
                review.getId(),
                review.getContent(),
                review.getWriter().getId(),
                review.getTarget().getId(),
                review.getRating(),
                review.getCreatedAt(),
                review.getUpdatedAt()
        );
    }
}
