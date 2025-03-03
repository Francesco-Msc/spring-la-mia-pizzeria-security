package org.lessons.java.spring_la_mia_pizzeria_relazioni.service;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    
    @Autowired
    private IngredientRepository ingredientRepo;

    public List<Ingredient> findAll(){
        return ingredientRepo.findAll();
    }

    public Ingredient getById(Integer id){
        return ingredientRepo.findById(id).get();
    }

    public Ingredient create(Ingredient newIngr){
        return ingredientRepo.save(newIngr);
    }

    public Ingredient update(Ingredient updateIngr){
        return ingredientRepo.save(updateIngr);
    }

    public void delete(Integer deleteIngrId){
        Ingredient ingredient = ingredientRepo.findById(deleteIngrId).get();
        for (Pizza pizza : ingredient.getPizzas()) {
            pizza.getIngredients().remove(ingredient);
        }
        ingredientRepo.deleteById(deleteIngrId);
    }

}
