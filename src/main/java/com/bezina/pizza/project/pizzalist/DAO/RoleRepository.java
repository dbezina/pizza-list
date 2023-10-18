package com.bezina.pizza.project.pizzalist.DAO;

import com.bezina.pizza.project.pizzalist.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);

    List<Role> findAllByNameIn(String... names);
}
