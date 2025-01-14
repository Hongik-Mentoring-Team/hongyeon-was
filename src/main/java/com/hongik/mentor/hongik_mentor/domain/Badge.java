package com.hongik.mentor.hongik_mentor.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Getter
@Entity
public class Badge {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String imageUrl;
}
