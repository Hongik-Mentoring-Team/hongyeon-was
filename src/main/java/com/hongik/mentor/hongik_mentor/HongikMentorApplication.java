package com.hongik.mentor.hongik_mentor;

import com.hongik.mentor.hongik_mentor.Initializer.BadgeInitializer;
import com.hongik.mentor.hongik_mentor.Initializer.TagInitializer;
import com.hongik.mentor.hongik_mentor.repository.BadgeRepository;
import com.hongik.mentor.hongik_mentor.repository.TagRepository;
import com.hongik.mentor.hongik_mentor.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class HongikMentorApplication {

	public static void main(String[] args) {
		SpringApplication.run(HongikMentorApplication.class, args);
	}


	//빈 등록
	@Bean
	@Profile({"local", "gno", "default"})
	public TagInitializer tagInitializer(@Autowired TagRepository tagRepository) {
		return new TagInitializer(tagRepository);
	}
	@Bean
	@Profile({"local", "gno", "default"})
	public BadgeInitializer badgeInitializer(@Autowired BadgeService badgeService) {
		return new BadgeInitializer(badgeService);
	}
}
