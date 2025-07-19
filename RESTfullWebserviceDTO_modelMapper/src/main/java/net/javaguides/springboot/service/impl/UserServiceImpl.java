package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    
    private ModelMapper modelMapper;
    
//    public UserServiceImpl() {
//		
//	}
//    
//    public UserServiceImpl(ModelMapper modelMapper) {
//		
//		this.modelMapper = modelMapper;
//	}
//
//	public UserServiceImpl(UserRepository userRepository) {
//		
//		this.userRepository = userRepository;
//	}
	
	

	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}	

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	public ModelMapper getModelMapper() {
		return modelMapper;
	}

	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	

	//save() does not return anything
	@Override
    public UserDto createUser(UserDto userDto) {
		
		//convert userDto into JPA entity	
		
		//map(source,destination)
		
		User user = modelMapper.map(userDto,User.class);
		
        User savedUser = userRepository.save(user);
		
        
        //convert user JPA entity to userDto
        
        UserDto saveUserDto = modelMapper.map(savedUser,UserDto.class);
          
        
        return saveUserDto;
	}

	//findById() - return type is optional
    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
		return modelMapper.map(user,UserDto.class);
    }

    //findAll() - return type is list<>
    @Override
    public List<UserDto> getAllUsers() {
       List<User> users = userRepository.findAll();
       return users.stream().map((user)-> modelMapper.map(user,UserDto.class))
    		   .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser,UserDto.class);
    }

    //deleteById() -  return type is void
    @Override
    public void deleteUser(Long userId) {
    	userRepository.deleteById(userId);
		
    }
}
