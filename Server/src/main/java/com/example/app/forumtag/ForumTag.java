package com.example.app.forumtag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tag")
public class ForumTag {

	@Id
	@Column(name="naziv")
	private String name;
	
	
	public ForumTag() {
		super();
	}

	public ForumTag(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
