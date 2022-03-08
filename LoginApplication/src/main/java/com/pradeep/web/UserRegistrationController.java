package com.pradeep.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pradeep.service.UserService;
import com.pradeep.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		System.out.println("UserRegistrationController.java - Constructor");
		this.userService = userService;
	}
	
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		System.out.println("UserRegistrationController.java - userRegistrationDto");
		return new UserRegistrationDto();
	}
	
	@GetMapping
	public String showRegistrationForm() {
		System.out.println("UserRegistrationController.java - showRegistrationForm");
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		System.out.println("UserRegistrationController.java - registerUserAccount");
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
}
