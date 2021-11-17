package com.ssafy.happyhouse.model.mapper;

import java.util.Map;

import com.ssafy.happyhouse.model.UserDto;

public interface UserMapper {

	void registerMember(UserDto userDto) throws Exception;
	UserDto login(Map<String, String> map) throws Exception;
	void updateUser(UserDto userDto) throws Exception;
	void deleteUser(String userid) throws Exception;

}
