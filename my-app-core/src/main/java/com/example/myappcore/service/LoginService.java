package com.example.myappcore.service;

import com.example.myappcore.dto.UserDto;
import com.example.myappcore.dto.UserCredentialsDto;
import com.example.myappcore.dto.UserRegistrationDto;
import com.example.myappcore.model.User;
import com.example.myappcore.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class LoginService {

    UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDto login(UserCredentialsDto userCredentialsDto){
        Optional<User> userOptional = userRepository.findByEmail(userCredentialsDto.email);
        if(userOptional.isPresent() && Objects.equals(userOptional.get().getPassword(), userCredentialsDto.password)){
            return new UserDto(userOptional.get());
        }
        return null;
    }

    @Transactional
    public boolean register(UserRegistrationDto userRegistrationDto){
        if(userRegistrationDto != null){
            Optional<User> userOptional = userRepository.findByEmail(userRegistrationDto.email);

            if (userOptional.isEmpty()){
                User user = new User();
                user.setEmail(userRegistrationDto.email);
                user.setLastname(userRegistrationDto.lastname);
                user.setFirstname(userRegistrationDto.firstname);
                user.setPassword(userRegistrationDto.password);

                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public boolean registerUser(UserDto userDto){
        userRepository.save(userDto.toEntity());
        return true;
    }
}
