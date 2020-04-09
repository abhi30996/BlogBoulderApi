package com.blogboulder.BlogBoulderApi.models.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * Entity class for User model.
 *
 * @author Abhishek Dudhrejia
 */

@Document(collection = "user")
public class User extends BaseEntity{

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private String dialCode;

	private String mobile;

	private String displayName;

	private boolean verified;

	public User() {
	}

	public User(String firstName, String lastName, String email, String password, String dialCode, String mobile, String displayName) {
//        setCreatedDate(new Date().getTime());
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.dialCode = dialCode;
		this.mobile = mobile;
		this.displayName = displayName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDialCode() {
		return dialCode;
	}

	public void setDialCode(String dialCode) {
		this.dialCode = dialCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
}
