package com.blog.bloggingapp.repositories;

import java.util.Optional;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.blog.bloggingapp.entities.User;
import com.blog.bloggingapp.payloads.UserDto;

public interface UserRepo extends JpaRepository<User,Integer>{

	Optional<User> findByEmail(String email);

}
