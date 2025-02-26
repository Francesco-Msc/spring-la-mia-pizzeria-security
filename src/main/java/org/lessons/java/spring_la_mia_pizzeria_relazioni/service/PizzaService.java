package org.lessons.java.spring_la_mia_pizzeria_relazioni.service;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {
    
    @Autowired
    private PizzaRepository pizzaRepo;

    public List<Pizza> findAll(){
        return pizzaRepo.findAll();
    }

    public Pizza getById(Integer id){
        return pizzaRepo.findById(id).get();
    }

    public List<Pizza> findByQuery(String query){
        return pizzaRepo.findByNameContainingOrDescriptionContaining(query, query);
    }

    public Pizza createPizza(Pizza addPizza){
        return pizzaRepo.save(addPizza);
    }

    public Pizza updatePizza(Pizza updatePizza){
        return pizzaRepo.save(updatePizza);
    }

    public void delete(Pizza pizza){
        pizzaRepo.delete(pizza);
    }
}
