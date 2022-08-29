package com.webdemo.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
		User user = new User(registrationDto.getFirstname(), registrationDto.getLastname(), registrationDto.getEmail(),
				passEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));

		return userRespository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRespository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));

	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
