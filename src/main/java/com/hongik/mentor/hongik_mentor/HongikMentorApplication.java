package com.hongik.mentor.hongik_mentor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HongikMentorApplication {

	public static void main(String[] args) {
		SpringApplication.run(HongikMentorApplication.class, args);
	}

}
