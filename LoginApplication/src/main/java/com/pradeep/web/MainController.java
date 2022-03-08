package com.pradeep.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/login")
	public String login() {
		System.out.println("MainController.java - login get");
		return "login";
	}
	
	@GetMapping("/")
	public String home() {
		System.out.println("MainController.java - / get");
		return "index";
	}
}
