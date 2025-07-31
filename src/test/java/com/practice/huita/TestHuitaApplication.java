package com.practice.huita;

import org.springframework.boot.SpringApplication;

public class TestHuitaApplication {

	public static void main(String[] args) {
		SpringApplication.from(HuitaApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
