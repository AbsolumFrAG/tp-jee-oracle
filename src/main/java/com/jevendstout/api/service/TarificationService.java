package com.jevendstout.api.service;

import com.jevendstout.api.entity.LigneDePanier;
import com.jevendstout.api.entity.Panier;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TarificationService {
    private final RestTemplate externalRestTemplate;

    public TarificationService(@Qualifier("externalRestTemplate") RestTemplate externalRestTemplate) {
        this.externalRestTemplate = externalRestTemplate;
    }

    public void mettreAJourTarifs(Panier panier) {
        for (LigneDePanier ligne : panier.getLignesDePanier()) {
            double prix = externalRestTemplate.getForObject("/anything/" + ligne.getArticle().getId(), Double.class);
            ligne.setPrixUnitaire(prix);
        }
    }
}
