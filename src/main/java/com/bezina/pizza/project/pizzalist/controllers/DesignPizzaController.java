package com.bezina.pizza.project.pizzalist.controllers;

import com.bezina.pizza.project.pizzalist.DAO.IngredientRepository;
import com.bezina.pizza.project.pizzalist.entity.Ingredient;
import com.bezina.pizza.project.pizzalist.entity.Pizza;
import com.bezina.pizza.project.pizzalist.entity.PizzaOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/design")
@Slf4j
@SessionAttributes("pizzaOrder")
@Controller
public class DesignPizzaController {
    @Autowired
    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignPizzaController(IngredientRepository ingredientRepository) {
        this.ingredientRepo = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        // Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        log.info("addIngredientsToModel");
        //   List<Ingredient> ingredients = (List<Ingredient>) ingredientRepo.findAll();
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        for (Ingredient ingredient : ingredients) {
            log.info(ingredient.toString());
        }

      /*  List<Ingredient> ingredients = Arrays.asList(
            new Ingredient(1,"CHED","Cheddar", Ingredient.Type.CHEESE),
            new Ingredient(2,"SALA","Salami",Ingredient.Type.MEAT),
            new Ingredient(3,"KTCH","Ketchup", Ingredient.Type.SAUCE),
            new Ingredient(4,"SLSA","Salsa", Ingredient.Type.SAUCE),
            new Ingredient(5,"TOMT","Tomato", Ingredient.Type.VEGGIES),
            new Ingredient(6,"ONIO","Onion", Ingredient.Type.VEGGIES)
        );*/
     /*   Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type: types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType((List<Ingredient>) ingredients, type));
        }*/
  /*      String[] types = {"MEAT", "CHEESE", "SAUCE", "VEGGIES"};
        for (String type : types) {
            model.addAttribute(type.toLowerCase(),
                    filterByType((List<Ingredient>) ingredients, type));
        }*/
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
//            log.info(type.toString().toLowerCase());
//            log.info(filterByType((List<Ingredient>) ingredients, type).toString());
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType((List<Ingredient>) ingredients, type));
        }

    }

    @ModelAttribute(name = "pizzaOrder")
    public PizzaOrder order() {
        return new PizzaOrder();
    }

    @ModelAttribute(name = "pizza")
    public Pizza pizza() {
        return new Pizza();
    }

    @GetMapping
    public String showDesignForm() {
        return "design-form";
    }

    /*  private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type){
          return ingredients
                  .stream()
                  .filter(x ->x.getType().equals(type))
                  .collect(Collectors.toList());
      }*/
    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @GetMapping({"/list"})
    public ModelAndView getAllEmployees() {
        ModelAndView mav = new ModelAndView("all");
        mav.addObject("ingredients", ingredientRepo.findAll());
        return mav;
    }

    @PostMapping
    public String processPizza(@Valid Pizza pizza,
                               Errors errors,
                               @ModelAttribute PizzaOrder pizzaOrder) {
        if (errors.hasErrors()) return "design-form";
        pizza.setIngredientsStr(pizza.getIngredientsString());
        pizza.setCreatedAt(new Date());
        pizza.setOrder_key(1L);
        pizzaOrder.addPizza(pizza);

        log.info("Processing pizza: {}", pizza);
        //  return "home";
        return "redirect:/orders/current";
        //    return new ModelAndView("redirect:/orders/current");


    }
}