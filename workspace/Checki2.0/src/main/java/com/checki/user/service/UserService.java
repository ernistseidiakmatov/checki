package com.checki.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.checki.user.domain.User;
import com.checki.user.domain.UserLoginRequest;
import com.checki.utils.JwtTokenUtil;

@Service 
public class UserService {

	@Autowired 
	private UserDao dao;
	
	@Value("${jwt.secret}")
	private String key;
	
	private Long expireTimeMs=1000*60*60L;

	public String login(UserLoginRequest user) {
		String token=null;
		
		try
		{
			User selectedUser= dao.findByIdAndPassword(user.getUser_id());
			
	
			
			if(selectedUser.getUser_id().equals(user.getUser_id())&&selectedUser.getUser_passwd().equals(user.getUser_passwd()))
			{
				token=JwtTokenUtil.createToken(selectedUser.getUser_name(), key, expireTimeMs);
			}
		}
		catch(Exception e)
		{
			token="아이디없음";
			
		}
		//로그인 성공
		

		return token;
	}
	
	public User find()
	{
		User result=dao.find();
		
		return result;
	}
	
}
