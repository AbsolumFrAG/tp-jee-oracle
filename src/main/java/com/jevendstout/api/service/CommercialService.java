package com.jevendstout.api.service;

import com.jevendstout.api.entity.Commercial;
import com.jevendstout.api.repository.CommercialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommercialService {
    private final CommercialRepository commercialRepository;

    @Autowired
    public CommercialService(CommercialRepository commercialRepository) {
        this.commercialRepository = commercialRepository;
    }

    public List<Commercial> findAllCommerciaux() {
        return commercialRepository.findAll();
    }

    public Optional<Commercial> findCommercialById(Long id) {
        return commercialRepository.findById(id);
    }

    public Commercial saveCommercial(Commercial commercial) {
        return commercialRepository.save(commercial);
    }

    public void deleteCommercial(Long id) {
        commercialRepository.deleteById(id);
    }
}
