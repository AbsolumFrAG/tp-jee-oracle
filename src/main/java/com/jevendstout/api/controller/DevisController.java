package com.jevendstout.api.controller;

import com.jevendstout.api.entity.Devis;
import com.jevendstout.api.service.DevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devis")
public class DevisController {
    private final DevisService devisService;

    @Autowired
    public DevisController(DevisService devisService) {
        this.devisService = devisService;
    }

    @GetMapping
    public List<Devis> getAllDevis() {
        return devisService.findAllDevis();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devis> getDevisById(@PathVariable Long id) {
        return devisService.findDevisById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Devis createDevis(@RequestBody Devis devis) {
        return devisService.saveDevis(devis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Devis> updateDevis(@PathVariable Long id, @RequestBody Devis devisDetails) {
        return devisService.findDevisById(id)
                .map(devis -> {
                    devis.setDate(devisDetails.getDate());
                    devis.setClientId(devisDetails.getClientId());
                    devis.setCommercialId(devisDetails.getCommercialId());
                    devis.setMontantTotalHT(devisDetails.getMontantTotalHT());
                    devis.setLigneDeDevis(devisDetails.getLigneDeDevis());

                    Devis updatedDevis = devisService.saveDevis(devis);
                    return ResponseEntity.ok(updatedDevis);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDevis(@PathVariable Long id) {
        return devisService.findDevisById(id)
                .map(devis -> {
                    devisService.deleteDevis(devis.getId());
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{devisId}/valider/{commercialId}")
    public ResponseEntity<?> validerDevis(@PathVariable Long commercialId, @PathVariable Long devisId) {
        try {
            Devis devis = devisService.validerDevis(commercialId, devisId);
            return ResponseEntity.ok(devis);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping("/{id}/rejeter")
    public ResponseEntity<?> rejeterDevis(@PathVariable Long id) {
        try {
            Devis devis = devisService.rejeterDevis(id);
            return ResponseEntity.ok(devis);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/{id}/valider-par-directeur")
    public ResponseEntity<?> validerDevisParDirecteur(@PathVariable Long id) {
        try {
            Devis devis = devisService.validerDevisParDirecteur(id);
            return ResponseEntity.ok(devis);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping("/valider-en-groupe")
    public ResponseEntity<?> validerDevisEnGroupe(@RequestBody List<Long> devisIds) {
        try {
            List<Devis> devisValides = devisService.validerDevisEnGroupe(devisIds);
            return ResponseEntity.ok(devisValides);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
