package com.checki.user.service;

import org.apache.ibatis.annotations.Mapper;

import com.checki.user.domain.User;

@Mapper
public interface UserDao {

	public User save(User user);

	public User find();

	public User findByIdAndPassword(String user_id);
}
