package com.blogboulder.BlogBoulderApi.models.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * Entity class for Blog data.
 *
 * @author Abhishek Dudhrejia
 */

@SuppressWarnings("unused")
@Document(collection = "blog")
public class Blog extends BaseEntity{

	private String content;

	private String userId;

	public Blog() {
	}


	public Blog(String content, String userId) {
		this.content = content;
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
