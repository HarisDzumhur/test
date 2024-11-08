package com.example.app.taghasforum;

import java.io.Serializable;

public class TagHasForumId implements Serializable{
	
	private String tagName;
	private Integer idTopic;
	public TagHasForumId(String tagName, Integer idTopic) {
		super();
		this.tagName = tagName;
		this.idTopic = idTopic;
	}
	public TagHasForumId() {
		super();
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public Integer getIdTopic() {
		return idTopic;
	}
	public void setIdTopic(Integer idTopic) {
		this.idTopic = idTopic;
	}
	
	

}
