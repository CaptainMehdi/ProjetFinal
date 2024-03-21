package com.example.myappcore.service;

import com.example.myappcore.dto.UserDetails;
import com.example.myappcore.dto.UserDto;
import com.example.myappcore.dto.UserRegistrationDto;
import com.example.myappcore.model.User;
import com.example.myappcore.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Objects;
import java.util.Optional;

@Service
public class LoginService {

    UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails login(UserDto userDto){
        Optional<User> userOptional = userRepository.findByEmail(userDto.email);
        if(userOptional.isPresent() && Objects.equals(userOptional.get().getPassword(), userDto.password)){
            return new UserDetails(userOptional.get());
        }
        return null;
    }

    public boolean register(UserRegistrationDto userRegistrationDto){
        Optional<User> userOptional = userRepository.findByEmail(userRegistrationDto.email);

        if (!userOptional.isPresent()){
            User user = new User();
            user.setEmail(userRegistrationDto.email);
            user.setLastname(userRegistrationDto.lastname);
            user.setFirstname(userRegistrationDto.firstname);
            user.setPassword(userRegistrationDto.password);

            userRepository.save(user);
            return true;
        }
        return false;
    }
}
