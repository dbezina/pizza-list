package com.bezina.pizza.project.pizzalist.DAO;

import com.bezina.pizza.project.pizzalist.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
