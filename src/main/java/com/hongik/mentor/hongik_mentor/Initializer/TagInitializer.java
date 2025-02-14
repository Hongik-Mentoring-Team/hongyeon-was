package com.hongik.mentor.hongik_mentor.Initializer;

import com.hongik.mentor.hongik_mentor.domain.post.Tag;
import com.hongik.mentor.hongik_mentor.repository.TagRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component    //main 어플리케이션 클래스에서 수동 빈 등록/
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

            tagRepository.saveAll(List.of(tag, tag1, tag2, tag3));

        }
    }
}
