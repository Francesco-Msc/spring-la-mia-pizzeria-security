package org.lessons.java.spring_la_mia_pizzeria_relazioni.repository;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    @Query("SELECT p FROM Pizza p JOIN p.ingredients i WHERE p.name LIKE %:query% OR i.name LIKE %:query%")
    List<Pizza> findByQuery(@Param("query") String query);
}