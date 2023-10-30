package com.bezina.pizza.project.pizzalist.DAO;

import com.bezina.pizza.project.pizzalist.entity.PizzaOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<PizzaOrder, Integer> {
     PizzaOrder save(PizzaOrder order);
}
