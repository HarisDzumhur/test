package com.example.app.reply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumReplyRepository extends JpaRepository<ForumReply, Integer>{

	public List<ForumReply> findByIdTopic(Integer idTopic);
	public List<ForumReply> findByreported(Boolean reported);
	public long deleteByIdResponseUser(Integer  idResponseUser);
}
