package com.blog.bloggingapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.bloggingapp.payloads.UserDto;

@Service
public interface UserService 
{
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUsetById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);

}
