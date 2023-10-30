package com.bezina.pizza.project.pizzalist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "ingredient_ref")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class IngredientRef {
    @Column(name = "ingredient_id")
    private final int ingredientID;
    @Column(name = "pizza_id")
    private final int pizzaID;

    private final String ingredient;
}
