package com.jevendstout.api.repository;

import com.jevendstout.api.entity.LigneDePanier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneDePanierRepository extends JpaRepository<LigneDePanier, Long> {
}
