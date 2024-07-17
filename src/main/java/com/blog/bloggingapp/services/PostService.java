package com.blog.bloggingapp.services;

import java.util.List;

import com.blog.bloggingapp.entities.Post;
import com.blog.bloggingapp.payloads.PostDto;
import com.blog.bloggingapp.payloads.PostResponse;

public interface PostService 
{
	
	//create
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update;
	public PostDto updatePostById(PostDto postDto,Integer postId);
	
	//delete
	
	public void deletePost(Integer postId);
	
	//get all posts;
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//get single post
	PostDto getPostById(Integer postId);
	
	//get all posts by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get all posts by user
	List<PostDto> getPostByUser(Integer userId);
	
	
	//search posts
	List<PostDto> searchPosts(String keyword);

}
