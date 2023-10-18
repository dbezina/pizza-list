package com.bezina.pizza.project.pizzalist.controllers;

import com.bezina.pizza.project.pizzalist.DAO.RoleRepository;
import com.bezina.pizza.project.pizzalist.DAO.UserRepository;
import com.bezina.pizza.project.pizzalist.entity.Role;
import com.bezina.pizza.project.pizzalist.entity.User;
import com.bezina.pizza.project.pizzalist.entity.RegistrationForm;
//import jakarta.validation.Valid;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@Slf4j
@SessionAttributes("registrationForm")
@RequestMapping("/register")
public class RegistrationController {
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(
            UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(@Valid RegistrationForm registrationForm,
                                      Errors errors,
                                      SessionStatus sessionStatus,
                                      Model model) {
        if (errors.hasErrors()) return "registration";
        try {
            //  userRepo.save(registrationForm.toUser(passwordEncoder));
            List<Role> roles = roleRepository.findAllByNameIn("USER"); // Implement this method in RoleRepository
            User user = registrationForm.toUser(passwordEncoder);
            user.setRoles(roles);
            userRepo.save(user);
            //  userRepo.save(user);
            log.info(" registrationForm submitted: {}", registrationForm);
            sessionStatus.setComplete();
            return "redirect:/login";
        } catch (Exception e) {
            // throw new RuntimeException(e);
            model.addAttribute("messageError", e.toString());
        } finally {
            return "redirect:/login";
        }
    }

}
