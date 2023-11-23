package com.jevendstout.api.service;

import com.jevendstout.api.entity.Panier;
import com.jevendstout.api.repository.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PanierService {
    private final PanierRepository panierRepository;

    @Autowired
    private LigneDePanierService ligneDePanierService;

    @Autowired
    private TarificationService tarificationService;

    public double calculerTotalPanier(Long panierId) {
        Panier panier = panierRepository.findById(panierId).orElse(null);
        if (panier == null) {
            return 0;
        }

        double total = panier.getLignesDePanier().stream()
                .mapToDouble(ligne -> ligne.getPrixUnitaire() * ligne.getQuantite())
                .sum();

        panier.setMontantTotalHT(total);
        panierRepository.save(panier);
        return total;
    }

    public Panier validerPanier(Long panierId) {
        Panier panier = panierRepository.findById(panierId).orElseThrow(() -> new RuntimeException("Panier non trouvé"));
        tarificationService.mettreAJourTarifs(panier);
        panier.setEstValide(true);
        return panierRepository.save(panier);
    }

    @Autowired
    public PanierService(PanierRepository panierRepository) {
        this.panierRepository = panierRepository;
    }

    public List<Panier> findAllPaniers() {
        return panierRepository.findAll();
    }

    public Optional<Panier> findPanierById(Long id) {
        return panierRepository.findById(id);
    }

    public Panier savePanier(Panier panier) {
        return panierRepository.save(panier);
    }

    public void deletePanier(Long id) {
        panierRepository.deleteById(id);
    }
}
