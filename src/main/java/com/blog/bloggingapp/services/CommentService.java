package com.blog.bloggingapp.services;

import com.blog.bloggingapp.payloads.CommentDto;

public interface CommentService 
{
	CommentDto createComment(CommentDto commentDto,Integer postId);
	void deletComment(Integer commentId);

}
