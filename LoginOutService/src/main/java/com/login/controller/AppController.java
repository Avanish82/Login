package com.login.controller;

import java.util.List; 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; 

import com.login.entity.User;
import com.login.repository.UserRepository;
 
@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) throws Exception{
		try {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		String mobile=user.getMobile();
		User mobileExist=userRepo.findByMobile(mobile); 
		if(mobileExist==null) { 
	     userRepo.save(user);
		}else {
			return "405";
		}
		} catch(Exception ex) { 
			return "404";
		}
		return "register_success";
	}
	
//	@PostMapping("/process_register")
//	public String processRegister(User user) throws Exception{
//		String me="";
//		try {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String encodedPassword = passwordEncoder.encode(user.getPassword());
//		user.setPassword(encodedPassword);
//		
//		String mobile=user.getMobile();
//		User mobileExist=userRepo.findByMobile(mobile);
////		Optional<User> mobileExist=userRepo.findByMobile(mobile);
//		 me=mobileExist.getMobile();
//		if(mobileExist==null) {
////		if(!mobile.equals(me)) {
//		User tst=userRepo.save(user);
//		}} catch(NullPointerException ex) {
//			User tst=userRepo.save(user);
////		}else {
////			return "405";
////		}
//		} catch(Exception ex) {
////			throw new UsernameNotFoundException("User not found");
////			throw new Exception("Email already exist");
//			return "404";
//		}
//		return "register_success";
////		return null;
//	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}
	
	@GetMapping("/alluserdetails")
	public String alluser(Model model) {
		List<User> listUsersDetails = userRepo.findAll();
		model.addAttribute("listUsersDetails", listUsersDetails);
		
		return "alluserdetails";
	}
}
