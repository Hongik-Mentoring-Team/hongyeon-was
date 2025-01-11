package com.hongik.mentor.hongik_mentor.Initializer;

import com.hongik.mentor.hongik_mentor.domain.Tag;
import com.hongik.mentor.hongik_mentor.repository.TagRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagInitializer {

    private final TagRepository tagRepository;

    @PostConstruct
    public void initTags() {
        if (tagRepository.count() == 0) {

            Tag tag = Tag.builder()
                    .name("금융권")
                    .build();

            Tag tag1 = Tag.builder()
                    .name("대기업 SI")
                    .build();

            Tag tag2 = Tag.builder()
                    .name("백엔드 개발자")
                    .build();

            Tag tag3 = Tag.builder()
                    .name("프론트엔드 개발자")
                    .build();

            tagRepository.save(tag);
            tagRepository.save(tag1);
            tagRepository.save(tag2);
            tagRepository.save(tag3);
        }
    }
}
