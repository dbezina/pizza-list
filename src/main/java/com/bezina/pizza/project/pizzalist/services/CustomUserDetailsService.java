package com.bezina.pizza.project.pizzalist.services;

import com.bezina.pizza.project.pizzalist.DAO.UserRepository;
import com.bezina.pizza.project.pizzalist.entity.CustomUserDetails;
import com.bezina.pizza.project.pizzalist.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // Replace with your User repository

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username); // Replace with your user retrieval logic
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        // Retrieve roles from your user_roles table and convert them to GrantedAuthority instances
        Collection<? extends GrantedAuthority> authorities = retrieveUserAuthorities(user);
        return new CustomUserDetails(user, authorities);
    }

    // Implement the method to retrieve user authorities from your user_roles table
    private Collection<? extends GrantedAuthority> retrieveUserAuthorities(User user) {
        // Fetch user roles from your database and convert them to GrantedAuthority objects
        // Example: Convert role names to SimpleGrantedAuthority
        return user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }
}
