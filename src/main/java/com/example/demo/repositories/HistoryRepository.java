package com.example.demo.repositories;

import com.example.demo.entities.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findAllByCreatedAtAndAndSourceCurrencyAndTargetCurrency(Date date, String source, String target);
}
