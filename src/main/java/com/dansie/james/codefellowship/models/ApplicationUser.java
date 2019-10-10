package com.dansie.james.codefellowship.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name="following_users",
            joinColumns = {@JoinColumn(name = "following_user")},
            inverseJoinColumns = {@JoinColumn(name = "followed_user")}
    )
    Set<ApplicationUser> usersIFollow;

    @ManyToMany(mappedBy = "usersIFollow")
    Set<ApplicationUser> usersThatFollowMe;

    public void followUser(ApplicationUser followedUser){
        usersIFollow.add(followedUser);
    }

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

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", posts=" + posts +
                ", usersIFollow=" + usersIFollow +
                ", usersThatFollowMe=" + usersThatFollowMe +
                '}';
    }
}
