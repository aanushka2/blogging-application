package com.blog.bloggingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.bloggingapp.payloads.ApiResponse;
import com.blog.bloggingapp.payloads.CategoryDto;
import com.blog.bloggingapp.payloads.UserDto;
import com.blog.bloggingapp.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController 
{
	
	@Autowired
	CategoryService categoryService;
	
	//create category
	@PostMapping("/")
	private ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		System.out.println("hello");
		CategoryDto createdCategory=this.categoryService.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(createdCategory,HttpStatus.CREATED);
		
	}
	
	//update
	@PutMapping("/{catId}")
	private ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer catId)
	{
		CategoryDto updatedCategory=this.categoryService.updateCategory(categoryDto,catId);
		
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
		
	}
	
	//delete
	@DeleteMapping("/{catId}")
	private ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId)
	{
		this.categoryService.deleteCategory(catId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfully",true),HttpStatus.OK);
		
	}
	
	
	//get single
	
	@GetMapping("/{catId}")
	private ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId)
	{
		CategoryDto categoryDto=this.categoryService.getCategory(catId);
		
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
		
	}
	
	//get all
	@GetMapping("/")
	private ResponseEntity<List<CategoryDto>> getAllCategory()
	{
		List<CategoryDto> categoryDto=this.categoryService.getAllCategories();
		
		//return new ResponseEntity<List<CategoryDto>>(categoryDto,HttpStatus.OK);
		return ResponseEntity.ok(categoryDto);
	}
	
}

