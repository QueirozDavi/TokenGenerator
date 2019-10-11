package com.tokegenerator;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableMongoAuditing
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
@EnableScheduling
@SpringBootApplication
public class TokenGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenGeneratorApplication.class, args);
	}

}
