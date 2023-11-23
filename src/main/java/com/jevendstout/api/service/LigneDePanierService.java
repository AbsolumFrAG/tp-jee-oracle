package com.jevendstout.api.service;

import com.jevendstout.api.entity.LigneDePanier;
import com.jevendstout.api.repository.LigneDePanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LigneDePanierService {
    private final LigneDePanierRepository ligneDePanierRepository;

    @Autowired
    public LigneDePanierService(LigneDePanierRepository ligneDePanierRepository) {
        this.ligneDePanierRepository = ligneDePanierRepository;
    }

    public List<LigneDePanier> findAllLignesDePanier() {
        return ligneDePanierRepository.findAll();
    }

    public Optional<LigneDePanier> findLigneDePanierById(Long id) {
        return ligneDePanierRepository.findById(id);
    }

    public LigneDePanier saveLigneDePanier(LigneDePanier ligneDePanier) {
        return ligneDePanierRepository.save(ligneDePanier);
    }

    public void deleteLigneDePanier(Long id) {
        ligneDePanierRepository.deleteById(id);
    }
}
