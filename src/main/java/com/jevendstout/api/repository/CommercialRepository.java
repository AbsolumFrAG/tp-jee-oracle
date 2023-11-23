package com.jevendstout.api.repository;

import com.jevendstout.api.entity.Commercial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommercialRepository extends JpaRepository<Commercial, Long> {
}
