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
    @Column
    private final Integer id;
    @Column
    private final String code;
    @Column
    private final String name;
    //   @Column
    //  private final String type;
    @Column
    @Enumerated(EnumType.STRING)
    private final Type type;

    public enum Type {
        @Enumerated(EnumType.STRING)
        MEAT, CHEESE, SAUCE, VEGGIES
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
