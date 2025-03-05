package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Offer;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.service.OfferService;
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

@Controller
@RequestMapping("/offers")
public class OfferController {
    
    @Autowired
    private OfferService offerService;

    @PostMapping("/create-offer")
    public String store(@Valid @ModelAttribute("offer") Offer addOffer, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            model.addAttribute("create", true);
            return "offers/create-edit-offer";
        }

        offerService.create(addOffer);
        return "redirect:/pizzas";
    }

    @GetMapping("/edit-offer/{id}")
    public String edit(@PathVariable("id") Integer id ,Model model){
        model.addAttribute("offer", offerService.getById(id));
        return "offers/create-edit-offer";
    }

    @PostMapping("/edit-offer/{id}")
    public String update(@PathVariable("id") Integer id, @Valid @ModelAttribute("offer") Offer updateOffer, BindingResult bindingResult, Model model){
        offerService.validateOfferDates(updateOffer, bindingResult);
        if (bindingResult.hasErrors()) {
            return "offers/create-edit-offer";
        }
        Offer offer = offerService.getById(id);
        Integer pizzaId = offer.getPizza().getId();
        offerService.updateOffer(updateOffer);
        return "redirect:/pizzas/" + pizzaId;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model){
        Offer offer = offerService.getById(id);
        Integer pizzaId = offer.getPizza().getId();
        offerService.delete(offer);
        return "redirect:/pizzas/" + pizzaId;
    }
}
