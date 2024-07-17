package com.blog.bloggingapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.bloggingapp.payloads.CategoryDto;

@Service
public interface CategoryService 
{
	
	//create
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	//delete 
	public void deleteCategory(Integer categoryId);
	
	//get single category
	CategoryDto getCategory(Integer categoryId);
	
	//get all category
	List<CategoryDto> getAllCategories();
	
	

}
