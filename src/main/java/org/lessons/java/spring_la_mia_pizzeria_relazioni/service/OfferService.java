package org.lessons.java.spring_la_mia_pizzeria_relazioni.service;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Offer;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepo;

    public Offer create(Offer addOffer){
        return offerRepo.save(addOffer);
    }
}
