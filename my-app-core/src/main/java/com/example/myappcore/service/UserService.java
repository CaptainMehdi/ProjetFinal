package com.example.myappcore.service;

import com.example.myappcore.dto.UserDto;
import com.example.myappcore.model.User;
import com.example.myappcore.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserById(Long id){
        User user = userRepository.getReferenceById(id);
        return new UserDto(user);
    }
}
