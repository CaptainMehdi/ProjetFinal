package com.example.myappcore;

import com.example.myappcore.dto.BainsLibreDto;
import com.example.myappcore.dto.CoursDto;
import com.example.myappcore.dto.UserDto;
import com.example.myappcore.dto.UserRegistrationDto;
import com.example.myappcore.model.BainsLibre;
import com.example.myappcore.model.Cours;
import com.example.myappcore.model.User;
import com.example.myappcore.service.ActivitePiscineService;
import com.example.myappcore.service.BainsLibreService;
import com.example.myappcore.service.CoursService;
import com.example.myappcore.service.LoginService;
import com.example.myappcore.utils.Bassin;
import com.example.myappcore.utils.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MyAppCoreApplication implements CommandLineRunner {

    private final LoginService loginService;
    private final ActivitePiscineService activitePiscineService;
    private final CoursService coursService;
    private final BainsLibreService bainsLibreService;
    public static void main(String[] args) {
        SpringApplication.run(MyAppCoreApplication.class, args);
    }

    public void run(String... args) throws Exception {

        // Register Users
        loginService.registerUser(new UserDto("mehdi@gmail.com", "mehdi", "Mehdi", "Diyani", Role.admin));
        loginService.registerUser(new UserDto("erika@gmail.com", "erika", "Erika", "Ross", Role.prof));

        User user = new User("silvie@gmail.com","silvie", "Silvie", "red", Role.prof);
        loginService.registerUser(new UserDto(user));

// Save Cours
        coursService.saveCours(new CoursDto(Bassin.petit, 6, null, null, "Requis 1", "Nageur 1"));
        coursService.saveCours(new CoursDto(Bassin.petit, 6, null, null, "Requis 2", "Nageur 2"));
        coursService.saveCours(new CoursDto(Bassin.petit, 6, null, null, "Requis 3", "Nageur 3"));
        coursService.saveCours(new CoursDto(Bassin.petit, 6, null, null, "Requis 4", "Nageur 4"));
        coursService.saveCours(new CoursDto(Bassin.petit, 6, null, null, "Requis 5", "Nageur 5"));

        coursService.saveCours(new CoursDto(Bassin.grand, 6, null, null, "Requis 1", "Nageur 1"));
        coursService.saveCours(new CoursDto(Bassin.grand, 6, null, null, "Requis 2", "Nageur 2"));
        coursService.saveCours(new CoursDto(Bassin.grand, 6, null, null, "Requis 3", "Nageur 3"));
        coursService.saveCours(new CoursDto(Bassin.grand, 6, null, null, "Requis 4", "Nageur 4"));
        coursService.saveCours(new CoursDto(Bassin.grand, 6, null, null, "Requis 5", "Nageur 5"));

// Save BainsLibre
        bainsLibreService.saveBainsLibre(new BainsLibreDto(Bassin.petit, null));
        bainsLibreService.saveBainsLibre(new BainsLibreDto(Bassin.grand, null));


        System.out.println("DONE");
    }

}
