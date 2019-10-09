package com.dansie.james.codefellowship.controllers;

import com.dansie.james.codefellowship.models.ApplicationUser;
import com.dansie.james.codefellowship.models.ApplicationUserRepository;
import com.dansie.james.codefellowship.models.Post;
import com.dansie.james.codefellowship.models.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class ProfileController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/profile")
    public String getProfile(Principal p, Model m){
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("user", loggedInUser);

        return "profile";
    }

    @PostMapping("/addPost")
    public RedirectView addPost(Principal p, String postBody){
        ApplicationUser postOwner = applicationUserRepository.findByUsername(p.getName());
        String date = LocalDateTime.now().toString();
        Post newPost = new Post(postBody, date, postOwner);

        postRepository.save(newPost);

        return new RedirectView("/profile");
    }
}
