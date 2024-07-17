package com.blog.bloggingapp.services.imp;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.blog.bloggingapp.entities.Category;
import com.blog.bloggingapp.entities.Post;
import com.blog.bloggingapp.entities.User;
import com.blog.bloggingapp.execption.ResourceNotFoundException;
import com.blog.bloggingapp.payloads.PostDto;
import com.blog.bloggingapp.payloads.PostResponse;
import com.blog.bloggingapp.repositories.CategoryRepo;
import com.blog.bloggingapp.repositories.PostRepo;
import com.blog.bloggingapp.repositories.UserRepo;
import com.blog.bloggingapp.services.PostService;

@Service
public class PostServiceImpl implements PostService{

	
	@Autowired 
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) 
	{
		
		User user = this.userRepo.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
		Post post=this.mapper.map(postDto, Post.class);
		post.setImgName("default.pnj");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(cat);
		
		Post newPost=this.postRepo.save(post);
		return this.mapper.map(newPost, PostDto.class);
		
				
	}

	@Override
	public PostDto updatePostById(PostDto postDto, Integer postId) 
	{
		System.out.println("heloo");
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post id", postId));
	
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImgName(postDto.getImgName());
		
		Post updatedPost=this.postRepo.save(post);
		PostDto postDto2=this.mapper.map(updatedPost, PostDto.class);
		return postDto2;
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post id", postId));
		this.postRepo.delete(post);
		
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) 
	{
		Sort s=null;
		if(sortDir.equalsIgnoreCase("asc"))
		{
			s=Sort.by(sortBy).ascending();
		}
		else
		{
			s=Sort.by(sortBy).descending();
		}
		Pageable pageable=PageRequest.of(pageNumber, pageSize,s);
		
		Page<Post> pagePost=this.postRepo.findAll(pageable);
		List<Post> allposts=pagePost.getContent();
		//List<Post> allposts=this.postRepo.findAll();
		
		List<PostDto> dtos = allposts.stream().map((post)->this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(dtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setLastPage(pagePost.isLast());
		postResponse.setTotalPages(pagePost.getTotalPages());
		
	
		return postResponse;
	}
	

	@Override
	public PostDto getPostById(Integer postId) {
		
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post_id",postId));
		PostDto postDto=this.mapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) 
	{
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
		List<Post> posts=this.postRepo.findByCategory(cat);
		List<PostDto> postDtos=posts.stream().map((post)->this.mapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		
		User user = this.userRepo.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));
		List<Post> posts=this.postRepo.findByUser(user);
		List<PostDto> postDtos=posts.stream().map((post)->this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) 
	{
		//String s="%"+keyword+"%";
		List<Post> posts=this.postRepo.findByTitleContaining(keyword);
		List<PostDto> dtos=posts.stream().map((post)->this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		// TODO Auto-generated method stub
		return dtos;
	}

}
