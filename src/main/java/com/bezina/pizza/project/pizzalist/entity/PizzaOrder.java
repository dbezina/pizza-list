package com.bezina.pizza.project.pizzalist.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "pizza_order")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class PizzaOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private Integer id;

    @Column
    private Date createdAt;

    /*@Column(name = "placed_at")
    private Date placedAt = new Date();*/

    @NotBlank(message = "Delivery name is required")
    @Column(name = "delivery_name")
    private String deliveryName;

    @NotBlank(message = "Delivery city is required")
    @Column(name = "delivery_city")
    private String deliveryCity;

    @NotBlank(message = "Delivery address is required")
    @Column(name = "delivery_address")
    private String deliveryAddress;

    @CreditCardNumber(message = "Not a valid credit card number")
    @Column(name = "cc_number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[1-2])([\\/])(2[0-9])$",
            message = "Must be MM/YY format")
    @Column(name = "cc_expiration")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    @Column
    private String ccCVV;

    @Min(value = 1, message = "You can't proceed order with no pizzas")
    @Column(name = "pizzas_amount")
    private int pizzasAmount = 0;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "pizzaOrder")
    private List<Pizza> pizzas = new ArrayList<>();

    public void addPizza(Pizza pizza) {
        this.setPizzasAmount(this.getPizzasAmount() + 1);
        pizza.setOrder_key((long) this.getPizzasAmount());
        pizza.setPizzaOrder(this);
        this.pizzas.add(pizza);
    }

    public void removePizza(int index) {
        this.pizzas.remove(index);
    }

    public Pizza removePizza(String pizzaName) {
        //this.pizzas.remove(new Pizza());
        Pizza deletedPizza = new Pizza();
        for (Pizza pizza : pizzas) {
            if (pizza.getName().equals(pizzaName)) {
                deletedPizza = pizza;
                this.pizzas.remove(pizza);
                return deletedPizza;
            }
        }
        return null;
    }

    /* public Pizza removePizza(Integer id) {
         //this.pizzas.remove(new Pizza());
         for (Pizza pizza : pizzas) {
             if (pizza.getId() == id) {
                 Pizza deletedItem = pizza;
                 this.pizzas.remove(pizza);
                 return deletedItem;
             }
         }
         return null;
     }*/
    public Pizza findPizzaByName(String pizzaName) {
        for (Pizza pizza : pizzas) {
            if (pizza.getName().equals(pizzaName)) {
                return pizza;
            }
        }
        return null;
    }

}
