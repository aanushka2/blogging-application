package com.blog.bloggingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.bloggingapp.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
