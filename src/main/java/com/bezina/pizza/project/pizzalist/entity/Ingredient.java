package com.bezina.pizza.project.pizzalist.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "ingredients")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Ingredient {

    @NotBlank
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Integer id;

    private final String code;

    private final String name;

    private final String type;

  /*  private final  Type type;


   public enum Type{
        MEAT, CHEESE, SAUCE, VEGGIES    }
*/

}
