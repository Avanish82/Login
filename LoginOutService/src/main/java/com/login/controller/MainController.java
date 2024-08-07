package com.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.login.dto.ResetPassword;
import com.login.entity.User;
import com.login.service.CustomUserDetailsService;

@Controller
public class MainController {

	@Autowired
	CustomUserDetailsService userDetailsService;
	
	@GetMapping("/logins")
	public String login() {
		return "login";
	}
	
	@GetMapping("/resetpass")
	public String showRegistrationForm1(Model model) {
		model.addAttribute("resetpass", new ResetPassword());
		return "reset";
	}
	
	@PostMapping("/resetpass")
	public String resetPassword(ResetPassword resetPass) {
	return userDetailsService.resetPassword(resetPass);
 
   }
	@GetMapping("/userValidate")
	public String userValidate(Model model) {
		model.addAttribute("resetpass", new ResetPassword());
		return "userValidate";
	}
	
	@PostMapping("/userValidate")
	public String userValidatePassword(String username) {
	return userDetailsService.validateUserExist(username);
   }
}

