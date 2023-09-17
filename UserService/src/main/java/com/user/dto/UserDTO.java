package com.user.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO
{
	private String id;
	private String name;
	private String email;
	private String about;
	
	private List<Rating> rating = new ArrayList<>();
}
