package com.example.app.forumtag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumTagRepository extends JpaRepository<ForumTag, String>{

}
