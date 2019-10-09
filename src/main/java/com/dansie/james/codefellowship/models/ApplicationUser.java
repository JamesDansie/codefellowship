package com.dansie.james.codefellowship.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String username;
    String password;
    String nameFirst;
    String nameLast;
    String dateOfBirth;
    String bio;

    public ApplicationUser(){}


    public ApplicationUser(String username, String password, String nameFirst, String nameLast, String dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.nameFirst = nameFirst;
        this.nameLast = nameLast;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    public List<Post> getPosts(){
        return posts;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    List<Post> posts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public String getNameLast() {
        return nameLast;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBio() {
        return bio;
    }
}
