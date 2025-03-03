package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

//trovare il modo di separare gli ingredienti con una virgola

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("ingredients", ingredientService.findAll());
        return "ingredients/index";
    }

    @GetMapping("/create-edit")
    public String create(Model model){
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("create", true);
        return "ingredients/create-edit";
    }

    @PostMapping("/create-edit")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient newIngr, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("create", true);
            return "ingredients/create-edit";
        }

        ingredientService.create(newIngr);
        return "redirect:/ingredients";
    }

    @GetMapping("/create-edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        model.addAttribute("ingredient", ingredientService.getById(id));
        return "ingredients/create-edit";
    }

    @PostMapping("/create-edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient updateIngr, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "ingredients/create-edit";
        }
        ingredientService.update(updateIngr);
        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model){
        ingredientService.delete(id);
        return "redirect:/ingredients";
    }
}
