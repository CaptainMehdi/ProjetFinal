package com.example.myappcore.service;

import com.example.myappcore.dto.UserDto;
import com.example.myappcore.model.User;
import com.example.myappcore.repository.UserRepository;
import com.example.myappcore.utils.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    public List<UserDto> getAllTeachers(){
        List<Role> roles = Arrays.asList(Role.prof, Role.admin);
        Optional<List<User>> users = userRepository.findAllByRoleIn(roles);
        List<UserDto> userDtos = new ArrayList<>();

        if(users.isPresent()){
            for(User user: users.get()){
                userDtos.add(new UserDto(user));
            }
        }

        return userDtos;
    }
}
