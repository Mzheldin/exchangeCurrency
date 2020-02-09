package com.example.demo.repositories;

import com.example.demo.entities.Valute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValuteRepository extends JpaRepository<Valute, Long> {
    Valute findById(String id);
}
