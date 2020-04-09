package com.blogboulder.BlogBoulderApi.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BlogDto extends BaseDto{

	private String id;

	private String content;

	@JsonProperty("user_id")
	private String userId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
