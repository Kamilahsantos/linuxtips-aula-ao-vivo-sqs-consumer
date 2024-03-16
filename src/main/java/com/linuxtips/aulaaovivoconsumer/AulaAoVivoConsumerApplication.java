package com.linuxtips.aulaaovivoconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling

@SpringBootApplication
public class AulaAoVivoConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AulaAoVivoConsumerApplication.class, args);
	}

}
