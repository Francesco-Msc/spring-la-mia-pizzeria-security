package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller.api;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/pizzas")
public class PizzasApiController {
    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<Pizza> index(){
        return pizzaService.findAll();
    }

    @GetMapping("{id}")
    public Pizza show(@PathVariable Integer id){
        return pizzaService.getById(id);
    }

    @PostMapping
    public Pizza store(@Valid @RequestBody Pizza pizza){
        return pizzaService.createPizza(pizza);
    }

    // verificare perche se in postman nel body viene omesso l'id non avviene un aggiornamento ma un'aggiunta di un nuovo dato
    @PutMapping("{id}")
    public Pizza update(@Valid @RequestBody Pizza pizza){
        return pizzaService.updatePizza(pizza);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id){
        pizzaService.delete(pizzaService.getById(id));
    }
}
