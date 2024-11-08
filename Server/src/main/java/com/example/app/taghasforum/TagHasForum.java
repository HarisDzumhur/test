package com.example.app.taghasforum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name="tag_has_tema_forum")
@IdClass (TagHasForumId.class)
public class TagHasForum {
	
@Id
@Column(name="naziv_taga")
private String tagName;

@Id
@Column(name="id_teme")
private Integer idTopic;



private Integer numTopics;

public TagHasForum() {
	super();
}

public TagHasForum(String tagName, Integer idTopic) {
	super();
	this.tagName = tagName;
	this.idTopic = idTopic;
}

public Integer getNumTopics() {
	return numTopics;
}

public void setNumTopics(Integer numTopics) {
	this.numTopics = numTopics;
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
