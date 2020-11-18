package com.blog.testproject.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}