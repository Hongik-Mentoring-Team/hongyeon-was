package com.hongik.mentor.hongik_mentor;

import com.hongik.mentor.hongik_mentor.Initializer.TagInitializer;
import com.hongik.mentor.hongik_mentor.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootTest
class HongikMentorApplicationTests {

	@Test
	void contextLoads() {
	}

	@Bean
	@Profile({"local", "gno", "default"})
	public TagInitializer tagInitializer(@Autowired TagRepository tagRepository) {
		return new TagInitializer(tagRepository);
	}

}
