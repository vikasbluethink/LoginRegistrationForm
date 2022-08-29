package com.webdemo.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.webdemo.dto.UserRegistrationDto;
import com.webdemo.model.User;


public interface UserService extends UserDetailsService {
	User save(UserRegistrationDto registrationDto);

}
