package com.example.app.taghasforum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagHasForumRepository extends JpaRepository<TagHasForum, TagHasForumId>{

	public List<TagHasForum> findBytagName(String tagName);
	public List<TagHasForum> findByidTopic(Integer idTopic);
}
