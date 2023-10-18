package com.bezina.pizza.project.pizzalist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PizzaListJPAApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(PizzaListJPAApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//WebMvcConfigurer.super.addViewControllers(registry);
		registry.addViewController("/").setViewName("home");
	}
}
