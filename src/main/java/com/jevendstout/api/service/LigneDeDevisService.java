package com.jevendstout.api.service;

import com.jevendstout.api.entity.LigneDeDevis;
import com.jevendstout.api.repository.LigneDeDevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LigneDeDevisService {
    private final LigneDeDevisRepository ligneDeDevisRepository;

    @Autowired
    public LigneDeDevisService(LigneDeDevisRepository ligneDeDevisRepository) {
        this.ligneDeDevisRepository = ligneDeDevisRepository;
    }

    public List<LigneDeDevis> findAllLignesDeDevis() {
        return ligneDeDevisRepository.findAll();
    }

    public Optional<LigneDeDevis> findLigneDeDevisById(Long id) {
        return ligneDeDevisRepository.findById(id);
    }

    public LigneDeDevis saveLigneDeDevis(LigneDeDevis ligneDeDevis) {
        return ligneDeDevisRepository.save(ligneDeDevis);
    }

    public void deleteLigneDeDevis(Long id) {
        ligneDeDevisRepository.deleteById(id);
    }
}
