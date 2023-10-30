package com.bezina.pizza.project.pizzalist.controllers;

import com.bezina.pizza.project.pizzalist.DAO.OrderRepository;
import com.bezina.pizza.project.pizzalist.entity.Pizza;
import com.bezina.pizza.project.pizzalist.entity.PizzaOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("pizzaOrder")
public class OrderController {
    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String orderFor() {
        return "order-form";
    }

    @PostMapping
    public String processOrder(@Valid PizzaOrder pizzaOrder,
                               Errors errors,
                               SessionStatus sessionStatus,
                               Model model) {
        if (errors.hasErrors()) return "order-form";
        try {
            pizzaOrder.setCreatedAt(new Date());
            orderRepo.save(pizzaOrder);
            log.info("Order submitted: {}", pizzaOrder);
            sessionStatus.setComplete();
            return "redirect:/";
        } catch (Exception e) {
            // throw new RuntimeException(e);
            model.addAttribute("messageError", e.toString());
        } finally {
            return "home";
        }
    }

  /*  @PostMapping("/remove")
    public String removePizza(@ModelAttribute PizzaOrder pizzaOrder,
                              @RequestParam String id,
                              Errors errors,
                              Model model) {
      /* if ( index >= 0 && index < pizzaOrder.getPizzas().size()) {
            pizzaOrder.removePizza(index);
        }*/

    /*  if (errors.hasErrors()) return "order-form";
      try {
          //  pizzaOrder.removePizza(name);
          //       Long longID = Long.getLong(id);
          Integer intID = Integer.getInteger(id);
          Pizza deletedPizza = pizzaOrder.removePizza(intID);
          model.addAttribute("deletedPizzaId", deletedPizza.getId());
          model.addAttribute("deletedPizzaName", deletedPizza.getName());
      } catch (Exception e) {
          model.addAttribute("messageError", e.toString());
      } finally {
          return "redirect:/orders/current";
      }
  }*/
    @PostMapping("/remove/{pizzaName}")
    public String removePizza(@PathVariable String pizzaName,
                              @ModelAttribute("pizzaOrder") PizzaOrder pizzaOrder,
                              Model model) {
        Pizza deletedPizza = pizzaOrder.removePizza(pizzaName);
        model.addAttribute("deletedPizza", deletedPizza);
        return "redirect:/orders/current";
    }

    @PostMapping("/undoRemove/{pizzaName}")
    public String undoRemovePizza(@PathVariable String pizzaName,
                                  @ModelAttribute("pizzaOrder") PizzaOrder pizzaOrder,
                                  Model model) {
        //  Pizza deletedPizza = pizzaOrder.undoRemovePizza(pizzaOrder, pizzaName);
        Pizza unDeletedPizza = new Pizza();
        model.getAttribute("deletedPizza");
        pizzaOrder.addPizza(unDeletedPizza);
        model.addAttribute("deletedPizza", null); // Clear the deleted pizza
        return "redirect:/orders/current";
    }
}
