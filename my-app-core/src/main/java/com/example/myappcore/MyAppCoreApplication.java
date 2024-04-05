package com.example.myappcore;

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

        //Users
        loginService.registerUser(new User("mehdi@gmail.com","mehdi","Mehdi","Diyani", Role.admin));
        loginService.registerUser(new User("erika@gmail.com","erika","Erika","Ross",Role.prof));
        //Cours
        for (int i = 1; i <= 9; i++) {
            Cours cours = new Cours();
            cours.setEleve(null);
            cours.setRequis("Requis"+i);
            cours.setNom("Nageur " + i);
            cours.setBassin(Bassin.petit);
            cours.setProf(null);
            cours.setMaxEleves(6);
            coursService.saveCours(cours);
        }
        for (int i = 1; i <= 9; i++) {
            Cours cours = new Cours();
            cours.setEleve(null);
            cours.setRequis("Requis"+i);
            cours.setNom("Nageur " + i);
            cours.setBassin(Bassin.grand);
            cours.setProf(null);
            cours.setMaxEleves(6);
            coursService.saveCours(cours);
        }
        //BainsLibre
        for (int i = 0; i < 2; i++) {
            BainsLibre bainsLibre = new BainsLibre();
            bainsLibre.setBassin(Bassin.petit);
            bainsLibre.setSauveteurs(null);
            bainsLibreService.saveBainsLibre(bainsLibre);
        }
        for (int i = 0; i < 2; i++) {
            BainsLibre bainsLibre = new BainsLibre();
            bainsLibre.setBassin(Bassin.grand);
            bainsLibre.setSauveteurs(null);
            bainsLibreService.saveBainsLibre(bainsLibre);
        }
        System.out.println("DONE");
    }

}
