package com.bezina.pizza.project.pizzalist.services;

import com.bezina.pizza.project.pizzalist.DAO.IngredientRepository;
import com.bezina.pizza.project.pizzalist.entity.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class IngredientByCodeConverter implements Converter<String, Ingredient> {
    private Map<String, Ingredient> ingredientMap = new HashMap<>();
    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByCodeConverter() {
      /* ingredientMap.put("CHED",
                new Ingredient(1,"CHED","Cheddar", Ingredient.Type.CHEESE));
        ingredientMap.put("SALA",
                new Ingredient(2,"SALA","Salami",Ingredient.Type.MEAT));
        ingredientMap.put("KTCH",
                new Ingredient(3,"KTCH","Ketchup", Ingredient.Type.SAUCE));
        ingredientMap.put("SLSA",
                new Ingredient(4,"SLSA","Salsa", Ingredient.Type.SAUCE));
        ingredientMap.put("TOMT",
                new Ingredient(5,"TOMT","Tomato", Ingredient.Type.VEGGIES));
        ingredientMap.put("ONIO",
                new Ingredient(6,"ONIO","Onion", Ingredient.Type.VEGGIES));

       */
        log.info("IngredientByCodeConverter");
        ingredientMap.put("CHED",
                new Ingredient(13, "CHED", "Cheddar", Ingredient.Type.CHEESE));
        ingredientMap.put("JACK",
                new Ingredient(14, "JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
        ingredientMap.put("CARN",
                new Ingredient(10, "CARN", "Carnitas", Ingredient.Type.MEAT));
        ingredientMap.put("GRBF",
                new Ingredient(9, "GRBF", "Ground Beef", Ingredient.Type.MEAT));
        ingredientMap.put("KTCH",
                new Ingredient(3, "KTCH", "Ketchup", Ingredient.Type.SAUCE));
        ingredientMap.put("SLSA",
                new Ingredient(15, "SLSA", "Salsa", Ingredient.Type.SAUCE));
        ingredientMap.put("SRCR",
                new Ingredient(16, "SRCR", "Sour Cream", Ingredient.Type.SAUCE));
        ingredientMap.put("TOMT",
                new Ingredient(11, "TOMT", "Diced Tomatoes", Ingredient.Type.VEGGIES));
        ingredientMap.put("LETC",
                new Ingredient(12, "LETC", "Lettuce", Ingredient.Type.VEGGIES));
        ingredientMap.put("ONIO",
                new Ingredient(17, "ONIO", "Onion", Ingredient.Type.VEGGIES));

    }

    @Override
    public Ingredient convert(String code) {
        return ingredientMap.get(code);
    }
}
