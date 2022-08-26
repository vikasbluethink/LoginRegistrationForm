package com.webdemo.service;


import com.webdemo.dto.UserRegistrationDto;
import com.webdemo.model.User;


public interface UserService {
	User save(UserRegistrationDto registrationDto);

}
