package com.jevendstout.api.controller;

import com.jevendstout.api.entity.LigneDeDevis;
import com.jevendstout.api.service.LigneDeDevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lignesDeDevis")
public class LigneDeDevisController {
    private final LigneDeDevisService ligneDeDevisService;

    @Autowired
    public LigneDeDevisController(LigneDeDevisService ligneDeDevisService) {
        this.ligneDeDevisService = ligneDeDevisService;
    }

    @GetMapping
    public List<LigneDeDevis> getAllLignesDeDevis() {
        return ligneDeDevisService.findAllLignesDeDevis();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LigneDeDevis> getLigneDeDevisById(@PathVariable Long id) {
        return ligneDeDevisService.findLigneDeDevisById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LigneDeDevis createLigneDeDevis(@RequestBody LigneDeDevis ligneDeDevis) {
        return ligneDeDevisService.saveLigneDeDevis(ligneDeDevis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LigneDeDevis> updateLigneDeDevis(@PathVariable Long id, @RequestBody LigneDeDevis ligneDeDevisDetails) {
        return ligneDeDevisService.findLigneDeDevisById(id)
                .map(ligneDeDevis -> {
                    ligneDeDevis.setDevis(ligneDeDevisDetails.getDevis());
                    ligneDeDevis.setArticle(ligneDeDevisDetails.getArticle());
                    ligneDeDevis.setQuantite(ligneDeDevisDetails.getQuantite());
                    ligneDeDevis.setPrixUnitaire(ligneDeDevisDetails.getPrixUnitaire());
                    LigneDeDevis updatedLigneDeDevis = ligneDeDevisService.saveLigneDeDevis(ligneDeDevis);
                    return ResponseEntity.ok(updatedLigneDeDevis);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLigneDeDevis(@PathVariable Long id) {
        return ligneDeDevisService.findLigneDeDevisById(id)
                .map(ligneDeDevis -> {
                    ligneDeDevisService.deleteLigneDeDevis(ligneDeDevis.getId());
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
