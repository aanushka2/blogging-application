package com.blog.bloggingapp.services.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.bloggingapp.entities.Category;
import com.blog.bloggingapp.execption.ResourceNotFoundException;
import com.blog.bloggingapp.payloads.CategoryDto;
import com.blog.bloggingapp.repositories.CategoryRepo;
import com.blog.bloggingapp.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService
{
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) 
	{
		Category cat=this.modelMapper.map(categoryDto, Category.class);
		Category addedCat=this.categoryRepo.save(cat);
		
		return this.modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) 
	{
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCat=this.categoryRepo.save(cat);
		return this.modelMapper.map(updatedCat,CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) 
	{
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category id", categoryId));
		this.categoryRepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) 
	{
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category id", categoryId));
		
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() 
	{
		List<Category> categories=this.categoryRepo.findAll();
		
		List<CategoryDto> categoryDtos=categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return categoryDtos;
	}

}
