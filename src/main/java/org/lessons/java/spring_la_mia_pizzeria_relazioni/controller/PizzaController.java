package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;


import java.time.LocalDate;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Offer;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.service.IngredientService;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private IngredientService ingredientService;
    
    @GetMapping
    public String homepage(Model model){
        model.addAttribute("pizze", pizzaService.findAll());
        model.addAttribute("isHome", true);
        return "homepage/index";
    }

    @GetMapping("/{id}")
    public String detailpage(@PathVariable("id") Integer id, Model model){
        model.addAttribute("pizze", pizzaService.getById(id));
        return "homepage/show";
    }

    @GetMapping("/search")
    public String findByKeyword(@RequestParam(name = "query") String query, Model model){
        model.addAttribute("pizze", pizzaService.findByQuery(query));
        model.addAttribute("isHome", true);
        return "homepage/index";
    }

    @GetMapping("/search-price")
    public String findByPrice(@RequestParam(name = "minPrice", required = false) Double minPrice, @RequestParam(name = "maxPrice", required = false) Double maxPrice, Model model) {
        model.addAttribute("isHome", true);
        if (((minPrice != null) && (minPrice >= 0)) && ((maxPrice != null) && (maxPrice >0))) {
            model.addAttribute("pizze", pizzaService.findByPriceRange(minPrice, maxPrice));
        } else {
            model.addAttribute("pizze", pizzaService.findAll());
        }
    return "homepage/index";
}
    
    @GetMapping("/create-edit")
    public String create(Model model){
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("create", true);
        model.addAttribute("ingredients", ingredientService.findAll());
        return "homepage/create-edit";
    }

    @PostMapping("/create-edit")
    public String store(@Valid @ModelAttribute("pizza") Pizza addPizza, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            model.addAttribute("create", true);
            model.addAttribute("ingredients", ingredientService.findAll());
            return "homepage/create-edit";
        }

        pizzaService.createPizza(addPizza);
        return "redirect:/pizzas";
    }

    @GetMapping("/create-edit/{id}")
    public String edit(@PathVariable("id") Integer id ,Model model){
        model.addAttribute("pizza", pizzaService.getById(id));
        model.addAttribute("ingredients", ingredientService.findAll());
        return "homepage/create-edit";
    }

    @PostMapping("/create-edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza updatePizza, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientService.findAll());
            return "homepage/create-edit";
        }

        pizzaService.updatePizza(updatePizza);
        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model){
        pizzaService.delete(pizzaService.getById(id));;
        return "redirect:/pizzas";
    }

    @GetMapping("/offer/{id}")
    public String offer(@PathVariable Integer id, Model model){
        Offer offer = new Offer();
        offer.setPizza(pizzaService.getById(id));
        offer.setStartDate(LocalDate.now());
        model.addAttribute("offer", offer);
        model.addAttribute("create", true);
        return "offers/create-edit-offer";
    }
}
