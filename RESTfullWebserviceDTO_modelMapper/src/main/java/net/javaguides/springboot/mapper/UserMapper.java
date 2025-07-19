package net.javaguides.springboot.mapper;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;

public class UserMapper {

	
	//convert Jpa entity to userDto
	public static UserDto mapToUserDto(User user) {
		
		UserDto userDto = new UserDto(
		user.getId(),
		user.getFirstName(),
		user.getLastName(),
		user.getEmail()
	);
		return userDto;
		
	}
	
	//convert userDto to Jpa entity
     public static User mapToUser(UserDto userDto) {
		
		User user = new User(
				userDto.getId(),
				userDto.getFirstName(),
				userDto.getLastName(),
				userDto.getEmail()
	);
		return user;
		
	}
	
	
	
	
}
