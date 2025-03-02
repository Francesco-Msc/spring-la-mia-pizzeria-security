package org.lessons.java.spring_la_mia_pizzeria_relazioni.service;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    
    @Autowired
    private IngredientRepository ingredientRepo;

}
