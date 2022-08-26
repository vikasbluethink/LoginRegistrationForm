package com.webdemo.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.webdemo.dto.UserRegistrationDto;
import com.webdemo.model.Role;
import com.webdemo.model.User;
import com.webdemo.reppository.UserRespository;

@Service
public class UserServiceImpl implements UserService {

	private UserRespository userRespository;

	public UserServiceImpl(UserRespository userRespository) {
		super();
		this.userRespository = userRespository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User(registrationDto.getFirstname(), registrationDto.getLastname(), registrationDto.getEmail(),
				registrationDto.getPassword(), Arrays.asList(new Role("ROLE_USER")));

		return userRespository.save(user);
	}

}
