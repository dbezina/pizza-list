package com.bezina.pizza.project.pizzalist.DAO;

import com.bezina.pizza.project.pizzalist.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
   /* Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);*/
}
