package com.blog.bloggingapp.services.imp;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.bloggingapp.entities.Comment;
import com.blog.bloggingapp.entities.Post;
import com.blog.bloggingapp.execption.ResourceNotFoundException;
import com.blog.bloggingapp.payloads.CommentDto;
import com.blog.bloggingapp.repositories.CommentRepo;
import com.blog.bloggingapp.repositories.PostRepo;
import com.blog.bloggingapp.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {

		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
		
		Comment comment = this.mapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
//		Set<Comment> st=new HashSet<Comment>();
//		post.setComment(st);;
//		
		Comment savedComment = this.commentRepo.save(comment);
		
		CommentDto commentDto2 = this.mapper.map(savedComment, CommentDto.class);
		
		return commentDto2;
	}

	@Override
	public void deletComment(Integer commentId) {
		
		Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "comment id", commentId));
		this.commentRepo.delete(comment);
	}

}
