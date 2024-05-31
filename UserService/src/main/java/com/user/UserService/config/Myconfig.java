package com.user.UserService.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Myconfig {

	//create bean
	@Bean
	//to distribute load to services(we dont have to mention port in impl)
	@LoadBalanced
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

	
}
