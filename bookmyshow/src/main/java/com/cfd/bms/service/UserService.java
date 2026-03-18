package com.cfd.bms.service;

import com.cfd.bms.dto.UserDto;
import com.cfd.bms.model.User;
import com.cfd.bms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	private UserDto createUser(UserDto userDto){
		User user = mapToEntity(userDto);
		User saveUser = userRepository.save(user);
		return mapToDto(saveUser);
	}

	private UserDto mapToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPhoneNumber(user.getPhoneNumber());
		return userDto;
	}

	private User mapToEntity(UserDto userDto) {
		User user = new User();
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPhoneNumber(userDto.getPhoneNumber());
		return  user;
	}
}
