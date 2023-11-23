package com.jevendstout.api.controller;

import com.jevendstout.api.entity.Commercial;
import com.jevendstout.api.service.CommercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commerciaux")
public class CommercialController {
    private final CommercialService commercialService;

    @Autowired
    public CommercialController(CommercialService commercialService) {
        this.commercialService = commercialService;
    }

    @GetMapping
    public List<Commercial> getAllCommerciaux() {
        return commercialService.findAllCommerciaux();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commercial> getCommercialById(@PathVariable Long id) {
        return commercialService.findCommercialById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Commercial createCommercial(@RequestBody Commercial commercial) {
        return commercialService.saveCommercial(commercial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commercial> updateCommercial(@PathVariable Long id, @RequestBody Commercial commercialDetails) {
        return commercialService.findCommercialById(id)
                .map(commercial -> {
                    commercial.setNom(commercialDetails.getNom());
                    commercial.setEmail(commercialDetails.getEmail());
                    commercial.setCategories(commercialDetails.getCategories());
                    Commercial updatedCommercial = commercialService.saveCommercial(commercial);
                    return ResponseEntity.ok(updatedCommercial);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCommercial(@PathVariable Long id) {
        return commercialService.findCommercialById(id)
                .map(commercial -> {
                    commercialService.deleteCommercial(commercial.getId());
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
