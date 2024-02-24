package com.core.toy3.src.comment.repository;


import com.core.toy3.src.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
