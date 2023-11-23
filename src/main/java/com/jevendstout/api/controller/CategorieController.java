package com.jevendstout.api.controller;

import com.jevendstout.api.entity.Categorie;
import com.jevendstout.api.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {
    private final CategorieService categorieService;

    @Autowired
    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping
    List<Categorie> getAllCategories() {
        return categorieService.findAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable Long id) {
        return categorieService.findCategorieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Categorie createCategorie(@RequestBody Categorie categorie) {
        return categorieService.saveCategorie(categorie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody Categorie categorieDetails) {
        return categorieService.findCategorieById(id)
                .map(categorie -> {
                    categorie.setNom(categorieDetails.getNom());
                    Categorie updatedCategorie = categorieService.saveCategorie(categorie);
                    return ResponseEntity.ok(updatedCategorie);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategorie(@PathVariable Long id) {
        return categorieService.findCategorieById(id)
                .map(categorie -> {
                    categorieService.deleteCategorie(categorie.getId());
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
