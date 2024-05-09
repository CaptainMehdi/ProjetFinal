package com.example.myappcore;

import com.example.myappcore.dto.*;
import com.example.myappcore.model.BainsLibre;
import com.example.myappcore.model.Cours;
import com.example.myappcore.model.User;
import com.example.myappcore.service.*;
import com.example.myappcore.utils.Bassin;
import com.example.myappcore.utils.Role;
import com.example.myappcore.utils.Type;
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
    private final NiveauService niveauService;
    public static void main(String[] args) {
        SpringApplication.run(MyAppCoreApplication.class, args);
    }

    public void run(String... args) throws Exception {

// Register Users
        loginService.registerUser(new UserDto("mehdi@gmail.com", "mehdi", "Mehdi", "Diyani", Role.admin));
        loginService.registerUser(new UserDto("erika@gmail.com", "erika", "Erika", "Ross", Role.prof));
        loginService.registerUser(new UserDto("silvie@gmail.com","silvie", "Silvie", "red", Role.prof));
        loginService.registerUser(new UserDto("john.doe@example.com", "john.doe", "John", "Doe", Role.prof));

        loginService.registerUser(new UserDto("alice@example.com", "alice", "Alice", "Smith", Role.eleve));
        loginService.registerUser(new UserDto("bob@example.com", "bob", "Bob", "Johnson", Role.eleve));
        loginService.registerUser(new UserDto("jane.doe@example.com", "jane.doe", "Jane", "Doe", Role.eleve));
        loginService.registerUser(new UserDto("charlie@example.com", "charlie", "Charlie", "Brown", Role.eleve));

// Save Niveau
        niveauService.saveNiveau(new NiveauDto("Requis 1", "Nageur 1", Bassin.petit));
        niveauService.saveNiveau(new NiveauDto("Requis 2", "Nageur 2", Bassin.petit));
        niveauService.saveNiveau(new NiveauDto("Requis 3", "Nageur 3", Bassin.petit));
        niveauService.saveNiveau(new NiveauDto("Requis 4", "Nageur 4", Bassin.petit));
        niveauService.saveNiveau(new NiveauDto("Requis 5", "Nageur 5", Bassin.petit));

        niveauService.saveNiveau(new NiveauDto("Requis 1", "Nageur 1", Bassin.grand));
        niveauService.saveNiveau(new NiveauDto("Requis 2", "Nageur 2", Bassin.grand));
        niveauService.saveNiveau(new NiveauDto("Requis 3", "Nageur 3", Bassin.grand));
        niveauService.saveNiveau(new NiveauDto("Requis 4", "Nageur 4", Bassin.grand));
        niveauService.saveNiveau(new NiveauDto("Requis 5", "Nageur 5", Bassin.grand));

// Save BainsLibre
        bainsLibreService.saveBainsLibre(new BainsLibreDto(Bassin.petit, Type.bainslibres, null));
        bainsLibreService.saveBainsLibre(new BainsLibreDto(Bassin.grand, Type.bainslibres, null));


        System.out.println("DONE");
    }

}
