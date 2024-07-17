package com.blog.bloggingapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.bloggingapp.payloads.ApiResponse;
import com.blog.bloggingapp.payloads.UserDto;
import com.blog.bloggingapp.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	//POST=create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createUserDto=this.userService.createUser(userDto);
		
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	//put-update user
	
	@PutMapping("/{userid}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable(value = "userid")Integer uid)
	{
		UserDto updatedUser=this.userService.updateUser(userDto, uid);
		return ResponseEntity.ok(updatedUser);
	}
	
	
	//delete user
	@DeleteMapping("/{userid}")
	public ResponseEntity<ApiResponse>deleteUser(@PathVariable("userid") Integer uid)
	{
		this.userService.deleteUser(uid);
		//return new ResponseEntity(Map.of("message","user deleted successfully"),HttpStatus.OK);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted succesfully",true),HttpStatus.OK);
	}
	
	//get -all user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	//get single user by id
	@GetMapping("/{userid}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userid)
	{
		return ResponseEntity.ok(this.userService.getUsetById(userid));
	}
	
	
	
	
	

}
