package com.example.codeArticle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CodeArticleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeArticleApplication.class, args);
	}

}
