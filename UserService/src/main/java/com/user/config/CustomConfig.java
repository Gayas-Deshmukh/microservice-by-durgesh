package com.user.config;

import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomConfig 
{
	@Bean
	public ModelMapper getMapper()
	{
		return new ModelMapper();
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate getResTemplate()
	{
		return new RestTemplate();
	}
}
