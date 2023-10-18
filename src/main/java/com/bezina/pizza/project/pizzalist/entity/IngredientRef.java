package com.bezina.pizza.project.pizzalist.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class IngredientRef {
    private final String ingredient;
}
