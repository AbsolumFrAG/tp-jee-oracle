package com.jevendstout.api.controller;

import com.jevendstout.api.entity.LigneDePanier;
import com.jevendstout.api.service.LigneDePanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lignesDePanier")
public class LigneDePanierController {
    private final LigneDePanierService ligneDePanierService;

    @Autowired
    public LigneDePanierController(LigneDePanierService ligneDePanierService) {
        this.ligneDePanierService = ligneDePanierService;
    }

    @GetMapping
    public List<LigneDePanier> getAllLignesDePanier() {
        return ligneDePanierService.findAllLignesDePanier();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LigneDePanier> getLigneDePanierById(@PathVariable Long id) {
        return ligneDePanierService.findLigneDePanierById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LigneDePanier createLigneDePanier(@RequestBody LigneDePanier ligneDePanier) {
        return ligneDePanierService.saveLigneDePanier(ligneDePanier);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LigneDePanier> updateLigneDePanier(@PathVariable Long id, @RequestBody LigneDePanier ligneDePanierDetails) {
        return ligneDePanierService.findLigneDePanierById(id)
                .map(ligneDePanier -> {
                    ligneDePanier.setPanier(ligneDePanierDetails.getPanier());
                    ligneDePanier.setArticle(ligneDePanierDetails.getArticle());
                    ligneDePanier.setQuantite(ligneDePanierDetails.getQuantite());
                    ligneDePanier.setPrixUnitaire(ligneDePanierDetails.getPrixUnitaire());
                    LigneDePanier updatedLigneDePanier = ligneDePanierService.saveLigneDePanier(ligneDePanier);
                    return ResponseEntity.ok(updatedLigneDePanier);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
