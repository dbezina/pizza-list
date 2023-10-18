package com.bezina.pizza.project.pizzalist.services;

import com.bezina.pizza.project.pizzalist.entity.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByCodeConverter implements Converter<String, Ingredient> {
    private Map<String, Ingredient> ingredientMap = new HashMap<>();

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
    }

    @Override
    public Ingredient convert(String code) {
        return ingredientMap.get(code);
    }
}
