package com.bezina.pizza.project.pizzalist.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "pizza")
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Pizza {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    private Date createdAt;
    @NotNull
    @Size(min = 5, message = "Please min 5 symbols")
    private String name;

    @NotNull
    @Size(min = 1, message = "You should choose at least 1 ingredient")
    @ManyToMany()
    private List<Ingredient> ingredients;

    public String getIngredientsString() {
        String result = "";
        for (Ingredient ingredient : this.getIngredients()) {
            result += ingredient.getCode() + " ";
        }
        return result;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
