package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.UserDTO;
import com.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@PostMapping()
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO)
	{
		 UserDTO user = this.userService.createUser(userDTO);
		 
		 return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("userId") String userId)
	{
		 UserDTO user = this.userService.getUser(userId);
		 
		 return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<UserDTO>> getAllUser()
	{
		  List<UserDTO> allUsers = this.userService.getAllUsers();
		 
		 return new ResponseEntity<List<UserDTO>>(allUsers, HttpStatus.OK);
	}
}
