package com.blog.bloggingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.bloggingapp.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

	
}
