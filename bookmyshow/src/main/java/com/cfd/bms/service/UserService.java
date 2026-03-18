package com.cfd.bms.service;

import com.cfd.bms.dto.UserDto;
import com.cfd.bms.exception.ResourceNotFoundException;
import com.cfd.bms.model.User;
import com.cfd.bms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	private UserDto createUser(UserDto userDto){
		User user = mapToEntity(userDto);
		User saveUser = userRepository.save(user);
		return mapToDto(saveUser);
	}

	private UserDto getUserById(Long id){
		User user = userRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User not found with id "+id));

		return  mapToDto(user);
	}

	public List<UserDto> getAllUsers(){
		List<User> users = userRepository.findAll();

		return users.stream()
				.map(this::mapToDto)
				.collect(Collectors.toList());
	}

	//TODO
	//update
	//delete

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
