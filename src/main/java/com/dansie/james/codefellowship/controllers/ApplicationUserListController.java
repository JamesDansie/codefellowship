package com.dansie.james.codefellowship.controllers;

import com.dansie.james.codefellowship.models.ApplicationUser;
import com.dansie.james.codefellowship.models.ApplicationUserRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class ApplicationUserListController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/user/{id}")
    public String getUserInfo(Model m, @PathVariable long id){
        ApplicationUser gottenUser =  applicationUserRepository.getOne(id);
        m.addAttribute("user", gottenUser);
        return "userDetails";
    }

    @GetMapping("/userList")
    public String getUserList(Model m, Principal p){
        List<ApplicationUser> userList = applicationUserRepository.findAll();
        m.addAttribute("userList", userList);
        return "allUsers";
    }

    @PostMapping("/follow")
    public RedirectView followUser(String id, Principal p){
        Long idL = Long.parseLong(id);
        ApplicationUser userFollowing = applicationUserRepository.findByUsername(p.getName());
        ApplicationUser userToBeFollowed = applicationUserRepository.getOne(idL);

        userFollowing.followUser(userToBeFollowed);
        applicationUserRepository.save(userFollowing);

        return new RedirectView("/profile");
    }

    @GetMapping("/feed")
    public String getFeed(Model m, Principal p){
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        Set<ApplicationUser> followList = currentUser.getUsersIFollow();

        m.addAttribute("listOfUsersIFollow", followList);
        return "feed";
    }
}
