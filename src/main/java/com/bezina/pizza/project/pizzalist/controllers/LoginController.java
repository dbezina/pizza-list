package com.bezina.pizza.project.pizzalist.controllers;

import com.bezina.pizza.project.pizzalist.DAO.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class LoginController implements WebMvcConfigurer {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login");
        registry.addViewController("/logout");
    }

  /*  @PostMapping("/login")
    public String processLogin(LoginForm form) {
        if ((form.getUsername() == userRepo.findByUsername(form.getUsername()).getUsername()) &&
                (passwordEncoder.encode(form.getPassword()) == passwordEncoder.encode(userRepo.findByUsername(form.getUsername()).getPassword())))
            return "/design";
        else return "redirect:/login ";
    }*/
/*  @PostMapping("/login")
  public String authenticate() {
      // Implement your authentication logic here
      // Check the submitted username and password, perform authentication, and set up security context

      // If authentication succeeds, you can redirect to a success page
      return "redirect:/design";

      // If authentication fails, you can redirect back to the login page with an error message
      // return "redirect:/login?error=true";
  }*/

}
