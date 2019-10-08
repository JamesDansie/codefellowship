package com.dansie.james.codefellowship.controllers;

import com.dansie.james.codefellowship.models.ApplicationUser;
import com.dansie.james.codefellowship.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ApplicationUserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @PostMapping("/signup")
    public RedirectView createNewUser(String username, String password, String nameFirst, String nameLast, String dateOfBirth, String bio){

        ApplicationUser newUser = new ApplicationUser(username, passwordEncoder.encode(password), nameFirst, nameLast, dateOfBirth, bio);

        applicationUserRepository.save(newUser);

        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/logout")
    public RedirectView logout(){

        return new RedirectView("/");
    }
}
