package com.web.personalskillhomework.repository;

import com.web.personalskillhomework.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
