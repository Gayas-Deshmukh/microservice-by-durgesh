package com.user.service;

import java.util.List;

import com.user.dto.UserDTO;

public interface UserService
{
	UserDTO createUser(UserDTO userDTO);
	
	UserDTO getUser(String userId);
	
	List<UserDTO> getAllUsers();
}
