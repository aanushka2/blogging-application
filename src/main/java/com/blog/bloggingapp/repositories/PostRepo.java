package com.blog.bloggingapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.bloggingapp.entities.Category;
import com.blog.bloggingapp.entities.Post;
import com.blog.bloggingapp.entities.User;
import com.blog.bloggingapp.payloads.PostDto;


//postrepo implementation class will be provided by jpa
public interface PostRepo extends JpaRepository<Post, Integer>
{
	//custome finder methods post
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);

	
	List<Post>findByTitleContaining(String title );
//	@Query("select p from post p where p.title like :key")
//	List<Post> searchByTitle(@Param("key") String title);
}
