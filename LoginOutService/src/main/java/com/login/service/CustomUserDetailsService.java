package com.login.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.login.dto.ResetPassword;
import com.login.entity.User;
import com.login.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	 @Autowired
	 PasswordEncoder encoder;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);
    	User mobile = userRepo.findByMobile(username);
		if (user!=null) {
			return new CustomUserDetails(user);
		}
		else if(mobile!=null) {
			return new CustomUserDetails(mobile);
		}
//		throw new UsernameNotFoundException("User not found");
		return null;
		
		 
	}

	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepo.findByEmail(username);
//		if (user == null) {
//			throw new UsernameNotFoundException("User not found");
//		}
//		return new CustomUserDetails(user);
//	}
	
	public String resetPassword(ResetPassword resetPass) {
		// TODO Auto-generated method stub
		String response="";
		String oldPassword=resetPass.getOldPassword();
		String username=resetPass.getUsername(); 
		User oldData = userRepo.findByEmail(username);
		String Userpd=oldData.getPassword(); 
         String oldp=encoder.encode(resetPass.getNewPassword());
          
          PasswordEncoder passencoder = new BCryptPasswordEncoder();
         Boolean status=  passencoder.matches(oldPassword, Userpd);
          if(status) { 	
        	  userRepo.resetPassword(oldp, resetPass.getUsername());
          response="password update successfully";
          System.out.println("password update successfully");
          return "psucess";
          }else {
        	  System.out.println("Current password is not valid");
        	  return "pass401";
          } 
	}


	public String validateUserExist(String username) {
		// TODO Auto-generated method stub
		User userEmail = userRepo.findByEmail(username);
    	User mobile = userRepo.findByMobile(username);
		if (userEmail!=null) {
			 return "reset";
		}
		else if(mobile!=null) {
			return "reset";
		}
//		throw new UsernameNotFoundException("User not found");
		return "error";
	}

}
