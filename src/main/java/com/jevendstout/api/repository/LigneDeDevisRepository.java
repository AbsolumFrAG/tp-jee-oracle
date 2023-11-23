package com.jevendstout.api.repository;

import com.jevendstout.api.entity.LigneDeDevis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneDeDevisRepository extends JpaRepository<LigneDeDevis, Long> {
}
