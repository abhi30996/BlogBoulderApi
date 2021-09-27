package com.blogboulder.BlogBoulderApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class BlogBoulderApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(BlogBoulderApiApplication.class, args);
	}
}
