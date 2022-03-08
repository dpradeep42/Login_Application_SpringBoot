package com.pradeep.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.pradeep.model.User;
import com.pradeep.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
	User save(UserRegistrationDto registrationDto);
}