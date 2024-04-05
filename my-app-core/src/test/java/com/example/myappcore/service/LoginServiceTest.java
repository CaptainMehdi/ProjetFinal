package com.example.myappcore.service;

import com.example.myappcore.dto.UserRegistrationDto;
import com.example.myappcore.dto.UserDetails;
import com.example.myappcore.dto.UserDto;
import com.example.myappcore.model.User;
import com.example.myappcore.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final LoginService loginService = new LoginService(userRepository);

    @Test
    public void testLogin_Successful() {
        String email = "mehdi@gmail.com";
        String password = "mehdi";

        UserDto userDto = new UserDto(email, password);
        UserDetails userDetails = loginService.login(userDto);
        System.out.println(userDetails);
        assertEquals(email, userDetails.getEmail());
    }

    @Test
    public void testLogin_InvalidEmail() {
        String email = "nonexistent@example.com";
        String password = "password123";
        UserDto userDto = new UserDto(email, password);
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        UserDetails userDetails = loginService.login(userDto);

        assertNull(userDetails);
    }

    @Test
    public void testLogin_IncorrectPassword() {
        String email = "test@example.com";
        String correctPassword = "correctPassword";
        String incorrectPassword = "incorrectPassword";
        UserDto userDto = new UserDto(email, incorrectPassword);
        User user = new User(email, correctPassword);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        UserDetails userDetails = loginService.login(userDto);

        assertNull(userDetails);
    }

    @Test
    public void testRegister_Successful() {
        String email = "newuser@example.com";
        String password = "password123";
        String lastname = "Doe";
        String firstname = "John";
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(email, password, lastname, firstname);
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        boolean result = loginService.register(userRegistrationDto);

        assertTrue(result);
    }

    @Test
    public void testRegister_EmailAlreadyExists() {
        String existingEmail = "existing@example.com";
        String password = "password123";
        String lastname = "Doe";
        String firstname = "John";
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(existingEmail, password, lastname, firstname);
        when(userRepository.findByEmail(existingEmail)).thenReturn(Optional.of(new User()));

        boolean result = loginService.register(userRegistrationDto);

        assertFalse(result);
    }

    @Test
    public void testRegister_NullDto() {
        UserRegistrationDto userRegistrationDto = null;

        boolean result = loginService.register(userRegistrationDto);

        assertFalse(result);
    }
}