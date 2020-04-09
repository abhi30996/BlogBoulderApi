package com.blogboulder.BlogBoulderApi.models.entities;

import ma.glasnost.orika.MapperFacade;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

import java.util.Date;

public class BaseEntity {

	private String id;

	private Date createdDate;

	@LastModifiedDate
	private Date updatedDate;

	private Date deletedDate;

	private boolean active = true;

	public BaseEntity(String id) {
		this.id = id;
	}

	public BaseEntity() {
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	@CreatedDate
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	//Getters and setters omitted for brevity

	public <S> S toDTO(Class<S> clazz, MapperFacade mapper) {
		try {
			return mapper.map(this, clazz);
		} catch (Exception e) {
			throw new RuntimeException(
					String.format(
							"Error converting to class %s, error %s",
							clazz.getTypeName(),
							e.getLocalizedMessage()
					)
			);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
