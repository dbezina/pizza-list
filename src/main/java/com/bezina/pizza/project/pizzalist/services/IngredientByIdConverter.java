package com.bezina.pizza.project.pizzalist.services;

import com.bezina.pizza.project.pizzalist.DAO.IngredientRepository;
import com.bezina.pizza.project.pizzalist.entity.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<Integer, Ingredient> {
    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient convert(Integer id) {
        return ingredientRepo.findById(id).orElse(null);
    }
}
