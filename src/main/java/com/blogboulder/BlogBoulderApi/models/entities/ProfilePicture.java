package com.blogboulder.BlogBoulderApi.models.entities;

//@Entity
//@Table
public class ProfilePicture extends BaseEntity {

	private Long id;

	private String path;

	private Long userId;

	private User user;
}
