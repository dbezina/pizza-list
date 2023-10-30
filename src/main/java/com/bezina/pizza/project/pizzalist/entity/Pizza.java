package com.bezina.pizza.project.pizzalist.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "pizza")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Pizza {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column
    private Integer id;
    @Column
    private Date createdAt;

    @NotNull
    @Size(min = 5, message = "Please min 5 symbols")
    @Column
    private String name;

    @NotNull
    @Size(min = 2, message = "You should choose at least 1 ingredient")
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Ingredient> ingredients;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pizza_order_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PizzaOrder pizzaOrder;

    public String getIngredientsString() {
        String result = "";
        for (Ingredient ingredient : this.getIngredients()) {
            result += ingredient.getCode() + " ";
        }
        return result;
    }

    @Column(name = "pizza_order_key")
    private Long order_key;

    @Column(name = "ingredients")
    private String IngredientsStr;

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", name='" + name + '\'' +
                ", pizzaOrderId=" + pizzaOrder.getId() +
                ", order_key=" + order_key +
                ", IngredientsStr='" + getIngredientsString() + '\'' +
                '}';
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
