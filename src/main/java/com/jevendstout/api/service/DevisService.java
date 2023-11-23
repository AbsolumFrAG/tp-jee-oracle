package com.jevendstout.api.service;

import com.jevendstout.api.entity.*;
import com.jevendstout.api.repository.CommercialRepository;
import com.jevendstout.api.repository.DevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DevisService {
    private final DevisRepository devisRepository;
    private final CommercialRepository commercialRepository;

    @Autowired
    public DevisService(DevisRepository devisRepository, CommercialRepository commercialRepository) {
        this.devisRepository = devisRepository;
        this.commercialRepository = commercialRepository;
    }

    public List<Devis> findAllDevis() {
        return devisRepository.findAll();
    }

    public Optional<Devis> findDevisById(Long id) {
        return devisRepository.findById(id);
    }

    public Devis saveDevis(Devis devis) {
        return devisRepository.save(devis);
    }

    public void deleteDevis(Long id) {
        devisRepository.deleteById(id);
    }

    public Devis validerDevis(Long commercialId, Long devisId) {
        Commercial commercial = commercialRepository.findById(commercialId).orElseThrow(() -> new RuntimeException("Commercial non trouvé"));
        Devis devis = devisRepository.findById(devisId).orElseThrow(() -> new RuntimeException("Devis non trouvé"));

        if (!peutValiderDevis(commercial, devis)) {
            throw new RuntimeException("Le commercial n'est pas autorisé à valider ce devis");
        }

        devis.setEstValide(true);
        return devisRepository.save(devis);
    }

    public Devis rejeterDevis(Long devisId) {
        Devis devis = devisRepository.findById(devisId).orElseThrow(() -> new RuntimeException("Devis non trouvé"));
        devis.setEstValide(false);
        return devisRepository.save(devis);
    }

    public boolean peutValiderDevis(Commercial commercial, Devis devis) {
        double totalPoids = 0;
        double poidsCategoriesResponsables = 0;

        for (LigneDeDevis ligne : devis.getLigneDeDevis()) {
            Article article = ligne.getArticle();
            Categorie categorieArticle = article.getCategorie();
            double lignePoids = ligne.getPrixUnitaire() * ligne.getQuantite();
            totalPoids += lignePoids;

            if (commercial.getCategories().contains(categorieArticle)) {
                poidsCategoriesResponsables += lignePoids;
            }
        }

        return poidsCategoriesResponsables > (totalPoids / 2);
    }

    public Devis validerDevisParDirecteur(Long devisId) {
        Devis devis = devisRepository.findById(devisId)
                .orElseThrow(() -> new RuntimeException("Devis non trouvé"));

        if (devis.getMontantTotalHT() > 10000) {
            devis.setEstValide(true);
            return devisRepository.save(devis);
        } else {
            throw new RuntimeException("Le devis ne nécessite pas de validation par le directeur commercial");
        }
    }

    public List<Devis> validerDevisEnGroupe(List<Long> devisIds) {
        List<Devis> devisValides = new ArrayList<>();

        for (Long id : devisIds) {
            Devis devis = validerDevisParDirecteur(id);
            devisValides.add(devis);
        }

        return devisValides;
    }
}
