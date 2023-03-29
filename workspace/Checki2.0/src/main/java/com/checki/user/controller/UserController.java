package com.checki.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.checki.user.domain.User;
import com.checki.user.domain.UserLoginRequest;
import com.checki.user.service.UserService;

@RestController
@RequestMapping("/user/*")
public class UserController {

	@Autowired
	private UserService userservice;

	
	
	@PostMapping("/Login")
	public ResponseEntity<?> Login(UserLoginRequest user)
	{
		String token=userservice.login(user);
		
		if(token==null||token=="아이디없음")
		{
			return ResponseEntity.badRequest().body("아이디 혹은 비밀번호가 틀림");
		}

		
		return ResponseEntity.ok().body(token);
	}
	

	
	
}
