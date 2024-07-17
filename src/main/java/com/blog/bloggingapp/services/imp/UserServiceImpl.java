package com.blog.bloggingapp.services.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.bloggingapp.entities.User;
import com.blog.bloggingapp.execption.ResourceNotFoundException;
import com.blog.bloggingapp.payloads.UserDto;
import com.blog.bloggingapp.repositories.UserRepo;
import com.blog.bloggingapp.services.UserService;

@Service
public class UserServiceImpl implements UserService 
{
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) 
	{
		User user=this.dtoToUser(userDto);
		
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
		
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
	    // Find the user by ID
	    User user = this.userRepo.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

	    // Update user information with the provided data
	    user.setName(userDto.getName());
	    user.setEmail(userDto.getEmail());
	    user.setPassword(userDto.getPassword());
	    user.setAbout(userDto.getAbout());

	    // Save the updated user
	    User updatedUser = this.userRepo.save(user);

	    // Convert the updated user to a DTO and return it
	    return this.userToDto(updatedUser);
	}


	@Override
	public UserDto getUsetById(Integer userId) 
	{
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() 
	{
		List<User> users=this.userRepo.findAll();
		
		List<UserDto> userDtos =users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());	
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) 
	{
		//this.userRepo.deleteById(userId);
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		this.userRepo.delete(user);
	}
	
	
	//model mapper
	User dtoToUser(UserDto userDto)
	{
//		User user=new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		User user=this.modelMapper.map(userDto, User.class);
		
		return user;
	}
	UserDto userToDto(User user)
	{
//		UserDto userDto=new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		UserDto userDto=this.modelMapper.map(user,UserDto.class);
		
		return userDto;
	}
	

}
