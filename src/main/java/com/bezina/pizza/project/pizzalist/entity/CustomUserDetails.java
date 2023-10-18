package com.bezina.pizza.project.pizzalist.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUserDetails extends User {

    private final User user;

    public CustomUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(),
                user.getFullname(), user.getCity(), user.getAddress(), user.getPhoneNumber(), user.getEnabled());
        this.user = user;
        this.user.setAuthorities(authorities);
    }


    public User getUser() {
        return user;
    }
}
