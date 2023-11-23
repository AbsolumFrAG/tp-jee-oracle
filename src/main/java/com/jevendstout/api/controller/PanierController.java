package com.jevendstout.api.controller;

import com.jevendstout.api.entity.Panier;
import com.jevendstout.api.service.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paniers")
public class PanierController {
    private final PanierService panierService;

    @Autowired
    public PanierController(PanierService panierService) {
        this.panierService = panierService;
    }

    @GetMapping("/{id}/calculerTotal")
    public ResponseEntity<Double> calculerTotalPanier(@PathVariable Long id) {
        double total = panierService.calculerTotalPanier(id);
        return ResponseEntity.ok(total);
    }

    @PostMapping("/{id}/valider")
    public ResponseEntity<?> validerPanier(@PathVariable Long id) {
        try {
            panierService.validerPanier(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public List<Panier> getAllPaniers() {
        return panierService.findAllPaniers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Panier> getPanierById(@PathVariable Long id) {
        return panierService.findPanierById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Panier createPanier(@RequestBody Panier panier) {
        return panierService.savePanier(panier);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Panier> updatePanier(@PathVariable Long id, @RequestBody Panier panierDetails) {
        return panierService.findPanierById(id)
                .map(panier -> {
                    panier.setClientId(panierDetails.getClientId());
                    panier.setLignesDePanier(panierDetails.getLignesDePanier());
                    panier.setMontantTotalHT(panierDetails.getMontantTotalHT());
                    Panier updatedPanier = panierService.savePanier(panier);
                    return ResponseEntity.ok(updatedPanier);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePanier(@PathVariable Long id) {
        return panierService.findPanierById(id)
                .map(panier -> {
                    panierService.deletePanier(panier.getId());
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
