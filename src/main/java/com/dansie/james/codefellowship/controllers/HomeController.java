package com.dansie.james.codefellowship.controllers;

import com.dansie.james.codefellowship.models.ApplicationUser;
import com.dansie.james.codefellowship.models.ApplicationUserRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/")
    public String getHome(Principal p, Model m){
        if(p != null){
            m.addAttribute("username", p.getName());
        }
        return "home";
    }

    @GetMapping("/registration")
    public String getRegistration(){
        return "registration";
    }

    @GetMapping("/profile")
    public String getProfile(Principal p, Model m){
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("user", loggedInUser);

        return "profile";
    }

}
