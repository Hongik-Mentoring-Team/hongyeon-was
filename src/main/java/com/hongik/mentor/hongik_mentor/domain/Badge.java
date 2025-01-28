package com.hongik.mentor.hongik_mentor.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor @EqualsAndHashCode
@Getter
@Entity
public class Badge {
    @Id @GeneratedValue
    @Column(name = "badge_id")
    private Long id;
    private String name;
    private String imageUrl;


    public Badge(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
